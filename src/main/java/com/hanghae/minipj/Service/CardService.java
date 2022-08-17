package com.hanghae.minipj.Service;


import com.hanghae.minipj.S3.S3Uploader;
import com.hanghae.minipj.domain.Card;
import com.hanghae.minipj.domain.Comment;
import com.hanghae.minipj.domain.Member;
import com.hanghae.minipj.dto.ResponseDto;
import com.hanghae.minipj.dto.request.CardRequestDto;
import com.hanghae.minipj.dto.response.CardResponseDto;
import com.hanghae.minipj.dto.response.CommentResponseDto;
import com.hanghae.minipj.jwt.TokenProvider;
import com.hanghae.minipj.repository.CardRepository;
import com.hanghae.minipj.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final CommentRepository commentRepository;
    private final TokenProvider tokenProvider;
    private final S3Uploader s3Uploader;

    @Transactional
    public ResponseDto<?> createCard(CardRequestDto requestDto, MultipartFile multiFile, HttpServletRequest request) throws IOException {

        if (null == request.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

        Member member = validateMember(request);
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }

        String imgUrl ="";
        if(multiFile!=null){
            imgUrl += s3Uploader.uploadFiles(multiFile, "static");
            System.out.println("to");
        }
        Card card = Card.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .imgUrl(imgUrl)
                .nickname(member.getNickname())
                .place(requestDto.getPlace())
                .star(requestDto.getStar())
                .ages(member.getAge())
                .member(member)
                .build();
        cardRepository.save(card);
        return ResponseDto.success(
                CardResponseDto.builder()
                        .id(card.getId())
                        .title(card.getTitle())
                        .content(card.getContent())
                        .imgUrl(card.getImgUrl())
                        .nickname(card.getMember().getNickname())
                        .star(card.getStar())
                        .place(card.getPlace())
                        .createdAt(card.getCreatedAt())
                        .build()
        );
    }

    @Transactional(readOnly = true)
    public ResponseDto<?> getCard(Long id) {
        Card card = isPresentCard(id);
        if (null == card) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 게시글 id 입니다.");
        }

        List<Comment> commentList = commentRepository.findAllByCardOrderByCreatedAtDesc(card);
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

        for (Comment comment : commentList) {
            commentResponseDtoList.add(
                    CommentResponseDto.builder()
                            .id(comment.getId())
                            .nickname(comment.getMember().getNickname())
                            .content(comment.getContent())
                            .createdAt(comment.getCreatedAt())
                            .build()
            );
        }
        List<CardResponseDto> cardList = new ArrayList<>();
        cardList.add(
                CardResponseDto.builder()
                        .id(card.getId())
                        .title(card.getTitle())
                        .content(card.getContent())
                        .nickname(card.getMember().getNickname())
                        .place(card.getPlace())
                        .star(card.getStar())
                        .imgUrl(card.getImgUrl())
                        .commentResponseDtoList(commentResponseDtoList)
                        .createdAt(card.getCreatedAt())
                        .build()
        );
        return ResponseDto.success(cardList);

    }

    @Transactional(readOnly = true)
    public ResponseDto<?> getAllCard() {
        List<Card> cardList = cardRepository.findAllByOrderByCreatedAtDesc();
        List<CardResponseDto> cardResponseDtoList = new ArrayList<>();

        for (Card card : cardList) {
            cardResponseDtoList.add(
                    CardResponseDto.builder()
                            .id(card.getId())
                            .title(card.getTitle())
                            .content(card.getContent())
                            .imgUrl(card.getImgUrl())
                            .nickname(card.getMember().getNickname())
                            .star(card.getStar())
                            .place(card.getPlace())
                            .createdAt(card.getCreatedAt())
                            .ages(card.getAges())
                            .build()

            );
        }

        return ResponseDto.success(cardResponseDtoList);
    }

    @Transactional
    public ResponseDto<?> updateCard(Long id, CardRequestDto requestDto, HttpServletRequest request) {

        if (null == request.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

        Member member = validateMember(request);
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }

        Card card = isPresentCard(id);
        if (null == card) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 게시글 id 입니다.");
        }

        if (card.validateMember(member)) {
            return ResponseDto.fail("UNAUTHORIZED", "작성자만 수정할 수 있습니다.");
        }

        card.update(requestDto,card);
        return ResponseDto.success(
                CardResponseDto.builder()
                        .id(card.getId())
                        .content(card.getContent())
                        .build()
        );
    }

    @Transactional
    public ResponseDto<?> deleteCard(Long id, HttpServletRequest request) {

        if (null == request.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

        Member member = validateMember(request);
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }

        Card card = isPresentCard(id);
        if (null == card) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 게시글 id 입니다.");
        }

        if (card.validateMember(member)) {
            return ResponseDto.fail("UNAUTHORIZED", "작성자만 삭제할 수 있습니다.");
        }

        cardRepository.delete(card);
        return ResponseDto.success("delete success");
    }


    @Transactional(readOnly = true)
    public Card isPresentCard(Long id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        return optionalCard.orElse(null);
    }

    @Transactional
    public Member validateMember(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("Authorization").substring(7))) {
            return null;
        }
        return tokenProvider.getMemberFromAuthentication();
    }


    public ResponseDto<?> getCardsByAges(String ages) {
        List<Card> cardList = cardRepository.findAllByAgesOrderByCreatedAtDesc(ages);
        return ResponseDto.success(cardList);
    }
}


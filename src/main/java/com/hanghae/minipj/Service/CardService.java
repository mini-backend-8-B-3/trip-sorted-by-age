package com.hanghae.minipj.Service;


import com.hanghae.minipj.response.ResponseDto;
import com.hanghae.minipj.domain.Card;
import com.hanghae.minipj.dto.CardRequestDto;
import com.hanghae.minipj.repository.CardRepository;
import com.hanghae.minipj.repository.CommentRepository;
import com.hanghae.minipj.response.CardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final CommentRepository commentRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public ResponseDto<?> createCard(CardRequestDto requestDto, HttpServletRequest request) {
        if (null == request.getHeader("Refresh-Token")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

        if (null == request.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

        Member member = validateMember(request);
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }

        Card card = Card.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .imgUrl(requestDto.getImgUrl())
                .nickname(member.getNickname())
                .ages(member.getNickname())
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
                        .modifiedAt(card.getModifiedAt())
                        .build()
        );
    }

    @Transactional(readOnly = true)
    public ResponseDto<?> getCard(Long id) {
        Card card = isPresentCard(id);
        if (null == card) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 게시글 id 입니다.");
        }

//        List<Comment> commentList = commentRepository.findAllByPost(post);
//        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
//
//        for (Comment comment : commentList) {
//            List<SubComment> subCommentList = subCommentRepository.findAllByComment(comment);
//            List<SubCommentResponseDto> subCommentResponseDtoList = new ArrayList<>();
//
//            for (SubComment subComment : subCommentList) {
//                subCommentResponseDtoList.add(
//                        SubCommentResponseDto.builder()
//                                .id(subComment.getId())
//                                .author(subComment.getMember().getNickname())
//                                .content(subComment.getContent())
//                                .likesCount(likeRepository.countBySubCommentId(subComment.getId()))
//                                .createdAt(subComment.getCreatedAt())
//                                .modifiedAt(subComment.getModifiedAt())
//                                .build()
//                );
//            }
//
//            commentResponseDtoList.add(
//                    CommentResponseDto.builder()
//                            .id(comment.getId())
//                            .author(comment.getMember().getNickname())
//                            .content(comment.getContent())
//                            .likesCount(likeRepository.countByCommentId(comment.getId()))
//                            .subCommentResponseDtoList(subCommentResponseDtoList)
//                            .createdAt(comment.getCreatedAt())
//                            .modifiedAt(comment.getModifiedAt())
//                            .build()
//            );
//        }

        return ResponseDto.success(
                CardResponseDto.builder()
                        .id(card.getId())
                        .title(card.getTitle())
                        .content(card.getContent())
                        .nickname(card.getMember().getNickname())
                        .imgUrl(card.getImgUrl())
                        .createdAt(card.getCreatedAt())
                        .modifiedAt(card.getModifiedAt())
                        .build()
        );
    }

    @Transactional(readOnly = true)
    public ResponseDto<?> getAllCard() {
        List<Card> cardList = cardRepository.findAllByOrderByModifiedAtDesc();
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
                            .modifiedAt(card.getModifiedAt())
                            .build()

            );
        }

        return ResponseDto.success(cardResponseDtoList);
    }

    @Transactional
    public ResponseDto<?> updateCard(Long id, CardRequestDto requestDto, HttpServletRequest request) {
        if (null == request.getHeader("Refresh-Token")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

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
            return ResponseDto.fail("BAD_REQUEST", "작성자만 수정할 수 있습니다.");
        }

        card.update(requestDto);
        return ResponseDto.success(
                CardResponseDto.builder()
                        .id(card.getId())
                        .nickname(card.getMember().getNickname())
                        .title(card.getTitle())
                        .content(card.getContent())
                        .imgUrl(card.getImgUrl())
                        .createdAt(card.getCreatedAt())
                        .modifiedAt(card.getModifiedAt())
                        .build()
        );
    }

    @Transactional
    public ResponseDto<?> deleteCard(Long id, HttpServletRequest request) {
        if (null == request.getHeader("Refresh-Token")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

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
            return ResponseDto.fail("BAD_REQUEST", "작성자만 삭제할 수 있습니다.");
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
        if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return null;
        }
        return tokenProvider.getMemberFromAuthentication();
    }


    public ResponseDto<?> getCardsByAges(String ages) {
        List<Card> cardList = cardRepository.findAllByAgesOrderByCreatedAtDesc(ages);
        return ResponseDto.success(cardList);
    }
}


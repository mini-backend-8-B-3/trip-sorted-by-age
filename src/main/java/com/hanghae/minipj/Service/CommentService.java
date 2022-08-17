package com.hanghae.minipj.Service;


import com.hanghae.minipj.dto.response.CommentResponseDto;
import com.hanghae.minipj.domain.Card;
import com.hanghae.minipj.domain.Comment;
import com.hanghae.minipj.domain.Member;
import com.hanghae.minipj.dto.ResponseDto;
import com.hanghae.minipj.dto.request.CommentRequestDto;
import com.hanghae.minipj.jwt.TokenProvider;
import com.hanghae.minipj.repository.CardRepository;
import com.hanghae.minipj.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CardRepository cardRepository;
    private final TokenProvider tokenProvider;

    public ResponseDto<?> createComment(HttpServletRequest request, CommentRequestDto requestDto,Long id) {

        if (null == request.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }
        Member member = validateMember(request);
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }
        if (requestDto.getContent() == null) {
            return ResponseDto.fail("CONTENT_EMPTY", "작성 칸이 비었습니다.");
        }
        Card card = isPresentCard(id);
        if(card == null){
            return ResponseDto.fail("CARD_NOT_FOUND", "해당 게시물이 존재하지 않습니다.");
        }
        Comment comment = new Comment(requestDto.getContent(), card, member);
        commentRepository.save(comment);
        CommentResponseDto commentResponseDto = CommentResponseDto.builder()
                .id(comment.getId())
                .nickname(comment.getMember().getNickname())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .isEditMode(comment.getIsEditMode())
                .build();
        return ResponseDto.success(commentResponseDto);
    }

    public ResponseDto<?> getComments() {
        List<CommentResponseDto> commentResponseDto = new ArrayList<>();
        for (Comment comment : commentRepository.findAllBy()) {
            commentResponseDto.add(
                    CommentResponseDto.builder()
                            .id(comment.getId())
                            .nickname(comment.getMember().getNickname())
                            .content(comment.getContent())
                            .createdAt(comment.getCreatedAt())
                            .build()
            );
        }
        return ResponseDto.success(commentResponseDto);
    }

    public ResponseDto<?> updateComment(Long id, HttpServletRequest request, CommentRequestDto requestDto){

        if (null == request.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }
        Member member = validateMember(request);
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }
        if (requestDto.getContent() == null) {
            return ResponseDto.fail("CONTENT_EMPTY", "작성 칸이 비었습니다.");
        }
        Comment comment = isPresentComment(id);
        if (null == comment) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 댓글 id 입니다.");
        }

        if (comment.validateMember(member)) {
            return ResponseDto.fail("UNAUTHORIZED", "작성자만 수정할 수 있습니다.");
        }
        comment.update(requestDto);
        CommentResponseDto commentResponseDto= CommentResponseDto.builder()
                .id(comment.getId())
                .nickname(comment.getMember().getNickname())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
        return ResponseDto.success(commentResponseDto);

    }

    public ResponseDto<?> deleteComment(Long id,HttpServletRequest request){

        if (null == request.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }
        Member member = validateMember(request);
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }

       Comment comment =isPresentComment(id);
        if (comment.validateMember(member)) {
            return ResponseDto.fail("UNAUTHORIZED", "작성자만 삭제할 수 있습니다.");
        }
        commentRepository.delete(comment);
        return ResponseDto.success(null);
    }

    @Transactional(readOnly = true)
    public Card isPresentCard(Long id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        return optionalCard.orElse(null);
    }
    @Transactional(readOnly = true)
    public Comment isPresentComment(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        return optionalComment.orElse(null);
    }

    @Transactional
    public Member validateMember(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("Authorization").substring(7))) {
            return null;
        }
        return tokenProvider.getMemberFromAuthentication();
    }

    public ResponseDto<?> getCommentByCardId(Long id) {
        List<Comment> commentList =commentRepository.findAllByCardIdOrderByCreatedAtDesc(id);
        List<CommentResponseDto> commentResponseDtoList =new ArrayList<>();
        for(Comment comment:commentList){
            commentResponseDtoList.add(
                    CommentResponseDto.builder()
                            .id(comment.getId())
                            .nickname(comment.getMember().getNickname())
                            .content(comment.getContent())
                            .createdAt(comment.getCreatedAt())
                            .isEditMode(comment.getIsEditMode())
                            .build()
            );
        }
        return ResponseDto.success(commentResponseDtoList);
    }
}

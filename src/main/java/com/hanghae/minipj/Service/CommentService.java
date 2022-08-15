package com.hanghae.minipj.Service;


import com.hanghae.minipj.Controller.CommentResponseDto;
import com.hanghae.minipj.domain.Card;
import com.hanghae.minipj.domain.Comment;
import com.hanghae.minipj.domain.Member;
import com.hanghae.minipj.dto.ResponseDto;
import com.hanghae.minipj.dto.request.CommentRequestDto;
import com.hanghae.minipj.jwt.TokenProvider;
import com.hanghae.minipj.repository.CardRepository;
import com.hanghae.minipj.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private CardRepository cardRepository;

    private TokenProvider tokenProvider;

    public ResponseDto<?> createComment(HttpServletRequest request, CommentRequestDto requestDto) {
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
        if (requestDto.getContent() == null) {
            ResponseDto.fail("CONTENT_EMPTY", "작성 칸이 비었습니다.");
        }
        Card card = isPresentCard(requestDto.getCardId());
        if(card == null){
            ResponseDto.fail("CARD_NOT_FOUND", "해당 게시물이 존재하지 않습니다.");
        }
        Comment comment = new Comment(requestDto.getContent(), card, member);
        commentRepository.save(comment);
        return ResponseDto.success(null);
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
        if (requestDto.getContent() == null) {
            ResponseDto.fail("CONTENT_EMPTY", "작성 칸이 비었습니다.");
        }
        Card card = isPresentCard(requestDto.getCardId());
        if(card == null){
            ResponseDto.fail("CARD_NOT_FOUND", "해당 게시물이 존재하지 않습니다.");
        }
        Comment comment = isPresentComment(id);
        if (null == comment) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 댓글 id 입니다.");
        }

        if (comment.validateMember(member)) {
            return ResponseDto.fail("BAD_REQUEST", "작성자만 수정할 수 있습니다.");
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
       Comment comment =isPresentComment(id);
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
        if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return null;
        }
        return tokenProvider.getMemberFromAuthentication();
    }
}

package com.hanghae.minipj.Controller;

import com.hanghae.minipj.Service.CommentService;
import com.hanghae.minipj.dto.ResponseDto;
import com.hanghae.minipj.dto.request.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@Validated
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/auth/comments")
    public ResponseDto<?> createComment(@RequestBody CommentRequestDto requestDto,
                                        HttpServletRequest request){
        return commentService.createComment(request,requestDto);
    }
    @GetMapping("/api/comments")
    public ResponseDto<?>getComments(){
        return commentService.getComments();
    }

    @PutMapping("/api/auth/comments/{id}")
    public ResponseDto<?> updateComment(@RequestBody CommentRequestDto requestDto, HttpServletRequest request, @PathVariable Long id){
        return commentService.updateComment(id, request,requestDto);
    }

    @DeleteMapping("/api/auth/comments/{id}")
    public ResponseDto<?> deleteComment(@PathVariable Long id, HttpServletRequest request){
        return commentService.deleteComment(id, request);
    }
}

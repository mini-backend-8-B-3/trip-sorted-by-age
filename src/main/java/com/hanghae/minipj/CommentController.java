package com.hanghae.minipj;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class CommentController {

    private CommentService commentService;

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
    public ResponseDto<?> updateComment(@RequestBody CommentRequestDto requestDto, HttpServletRequest request){
        return commentService.updateComment(request,requestDto);
    }

    @DeleteMapping("/api/auth/comments/{id}")
    public ResponseDto<?> deleteComment(@PathVariable Long id, HttpServletRequest request){
        return commentService.deleteComment(id, request);
    }
}

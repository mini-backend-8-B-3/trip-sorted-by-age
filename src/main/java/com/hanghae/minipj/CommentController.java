package com.hanghae.minipj;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}

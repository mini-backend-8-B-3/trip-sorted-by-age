package com.hanghae.minipj;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private int id;
    private String nickname;
    private String content;
    private LocalDateTime createdAt;

}

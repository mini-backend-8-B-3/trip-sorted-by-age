package com.hanghae.minipj.Controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentRequestDto {
    private Long cardId;
    private String content;
}

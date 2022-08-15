package com.hanghae.minipj.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentRequestDto {
    private Long cardId;
    private String content;
}

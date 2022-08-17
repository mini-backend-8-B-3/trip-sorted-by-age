package com.hanghae.minipj.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
@Builder
@Getter
public class CommentResponseDto {
    private Long id;
    private String nickname;
    private String content;
    private LocalDateTime createdAt;
    private Boolean isEditMode;

}

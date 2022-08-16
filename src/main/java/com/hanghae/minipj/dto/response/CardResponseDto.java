package com.hanghae.minipj.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardResponseDto {
    private Long id;
    private String title;
    private String content;
    private String nickname;
    private String imgUrl;
    private int star;
    private String ages;
    private String place;
    private LocalDateTime createdAt;
    private List<CommentResponseDto> commentResponseDtoList;
}
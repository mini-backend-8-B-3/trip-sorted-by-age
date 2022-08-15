package com.hanghae.minipj.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardRequestDto {
    private String title;
    private String content;
    private String imgUrl;
    private int star;
    private String place;
}

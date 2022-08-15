package com.hanghae.minipj.dto.response;


import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String nickname;
    private String password;
    private String gender;
    private String age;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}

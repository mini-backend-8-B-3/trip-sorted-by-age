package com.hanghae.minipj.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshToken extends Timestamped{

    @Id
    @Column(nullable = false)
    private Long id;

    @JoinColumn(name = "member_id",nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(name = "token_value", nullable = false)
    private String value;

    public void updateValue(String token){
        this.value = token;
    }

}

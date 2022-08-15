package com.hanghae.minipj.domain;

import com.hanghae.minipj.dto.request.CommentRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Builder
    public class Comment extends Timestamped {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @JoinColumn(name = "member_id", nullable = false)
        @ManyToOne(fetch = LAZY)
        private Member member;

        @JoinColumn(name = "card_id", nullable = false)
        @ManyToOne(fetch = LAZY)
        private Card card;


        @Column(nullable = false)
        private String content;

        public Comment(String content, Card card, Member member){
            this.id = getId();
            this.content=content;
            this.card =card;
            this.member =member;

        }

        public void update(CommentRequestDto commentRequestDto) {
            this.content = commentRequestDto.getContent();
        }

        public boolean validateMember(Member member) {
            return !this.member.equals(member);
        }
}

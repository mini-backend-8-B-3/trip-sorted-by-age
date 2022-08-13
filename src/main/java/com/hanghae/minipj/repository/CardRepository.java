package com.hanghae.minipj.repository;


import com.hanghae.minipj.domain.Card;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByOrderByModifiedAtDesc();
    List<Card> findAllByMemberId(Long memberId);


}

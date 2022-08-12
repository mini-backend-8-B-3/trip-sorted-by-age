package com.hanghae.minipj.repository;

import com.hanghae.minipj.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findById(Long id);
}

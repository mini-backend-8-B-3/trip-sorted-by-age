package com.hanghae.minipj.repository;


import com.hanghae.minipj.domain.Card;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("select DISTINCT m from Card m join fetch m.member")
    List<Card> findAllByOrderByCreatedAtDesc();

    @Query("select DISTINCT m from Card m join fetch m.member")
    List<Card> findAllByAgesOrderByCreatedAtDesc(String ages);
    Optional<Card> findById(Long id);
}

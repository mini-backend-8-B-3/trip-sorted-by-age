package com.hanghae.minipj.repository;


import com.hanghae.minipj.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("select DISTINCT m from Card m join fetch m.member ORDER BY m.createdAt DESC ")
    List<Card> findAllByOrderByCreatedAtDesc();

    @Query("select DISTINCT m from Card m join fetch m.member where m.ages in :ages ORDER BY m.createdAt DESC ")
    List<Card> findAllByAgesOrderByCreatedAtDesc(@Param("ages") String ages);
    Optional<Card> findById(Long id);
}

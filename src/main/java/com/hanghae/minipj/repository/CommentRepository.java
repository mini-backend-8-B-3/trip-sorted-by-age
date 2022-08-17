package com.hanghae.minipj.repository;

import com.hanghae.minipj.domain.Card;
import com.hanghae.minipj.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select DISTINCT m from Comment m join fetch m.member ORDER BY m.createdAt DESC ")
    List<Comment> findAllBy();
    @Query("select DISTINCT m from Comment m join fetch m.member where m.card in :card ORDER BY m.createdAt DESC ")
    List<Comment> findAllByCardOrderByCreatedAtDesc(@Param("card") Card card);
    @Query("select DISTINCT m from Comment m join fetch m.member where m.card.id in :cardId ORDER BY m.createdAt DESC ")
    List<Comment> findAllByCardIdOrderByCreatedAtDesc(@Param("cardId") Long cardId);
}

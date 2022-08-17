package com.hanghae.minipj.repository;

import com.hanghae.minipj.domain.Card;
import com.hanghae.minipj.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findById(Long id);

    @Query("select DISTINCT m from Comment m join fetch m.member")
    List<Comment> findAllBy();
    @Query("select DISTINCT m from Comment m join fetch m.member")
    List<Comment> findAllByCardOrderByCreatedAtDesc(Card card);
    @Query("select DISTINCT m from Comment m join fetch m.member")
    List<Comment> findAllByCardIdOrderByCreatedAtDesc(Long cardId);
}

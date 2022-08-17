package com.hanghae.minipj.repository;

import com.hanghae.minipj.domain.Card;
import com.hanghae.minipj.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findById(Long id);

    List<Comment> findAllBy();

    List<Comment> findAllByCard(Card card);

    List<Comment> findAllByCommentOrderByCreatedAtDesc(Comment comment);
}

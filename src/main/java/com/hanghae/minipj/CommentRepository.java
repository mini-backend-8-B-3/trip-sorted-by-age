package com.hanghae.minipj;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository {
    Optional<Comment> findById(Long id);
}

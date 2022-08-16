package com.hanghae.minipj.repository;

import com.hanghae.minipj.domain.AccessToken;
import com.hanghae.minipj.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessTokenRepository extends JpaRepository<AccessToken,Long> {
    Optional<AccessToken> findByMember(Member member);
}

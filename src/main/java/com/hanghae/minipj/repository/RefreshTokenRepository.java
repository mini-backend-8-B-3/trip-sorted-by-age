package com.hanghae.minipj.repository;

import com.hanghae.minipj.domain.Member;
import com.hanghae.minipj.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {

    Optional<RefreshToken> findByMember(Member member);

}

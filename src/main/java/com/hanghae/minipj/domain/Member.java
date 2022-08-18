package com.hanghae.minipj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.Hibernate;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Getter
public class Member extends Timestamped{

    public Member(){}//초기화 되는 걸로 JAVA reflection -기술

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String nickname;


    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String age;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Builder// 찾아보기
    public Member(String nickname){
        this.nickname =nickname;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || Hibernate.getClass(this)!= Hibernate.getClass(o)){
            return false;
        }
        Member member = (Member) o;
        return id != null && Objects.equals(id,member.id);
    }

    @Override
    public int hashCode(){
        return getClass().hashCode();
    }

    public boolean validatePassword(PasswordEncoder passwordEncoder, String password){
        return passwordEncoder.matches(password,this.password);
    }

}

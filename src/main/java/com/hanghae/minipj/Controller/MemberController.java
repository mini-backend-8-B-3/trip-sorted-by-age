package com.hanghae.minipj.controller;

import com.hanghae.minipj.dto.request.LoginRequestDto;
import com.hanghae.minipj.dto.request.MemberRequestDto;
import com.hanghae.minipj.dto.response.ResponseDto;
import com.hanghae.minipj.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @RequestMapping(value = "/api/member/signup",method = RequestMethod.POST)
    public ResponseDto<?> signup(@RequestBody @Valid MemberRequestDto requestDto){
        return memberService.creatMember(requestDto);
    }

    @RequestMapping(value = "/api/member/login",method = RequestMethod.POST)
    public ResponseDto<?> login(@RequestBody @Valid LoginRequestDto requestDto,
                                HttpServletResponse response){
        return memberService.login(requestDto,response);
    }

    @RequestMapping(value = "/api/member/logout",method = RequestMethod.POST)
    public ResponseDto<?> logout(HttpServletRequest request){
        return memberService.logout(request);
    }

}
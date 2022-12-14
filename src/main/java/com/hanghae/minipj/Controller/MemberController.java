package com.hanghae.minipj.Controller;


import com.hanghae.minipj.Service.MemberService;
import com.hanghae.minipj.dto.ResponseDto;
import com.hanghae.minipj.dto.request.LoginRequestDto;
import com.hanghae.minipj.dto.request.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @RequestMapping(value = "/users/signup",method = RequestMethod.POST)
    public ResponseDto<?> signup(@RequestBody @Valid MemberRequestDto requestDto){
        return memberService.creatMember(requestDto);
    }

    @RequestMapping(value = "/users/login",method = RequestMethod.POST)
    public ResponseDto<?> login(@RequestBody @Valid LoginRequestDto requestDto,
                                HttpServletResponse response){
        return memberService.login(requestDto,response);
    }

    @RequestMapping(value = "/users/logout",method = RequestMethod.POST)
    public ResponseDto<?> logout(HttpServletRequest request){
        return memberService.logout(request);
    }


    @RequestMapping(value = "/users/nickcheck",method = RequestMethod.POST)
    public ResponseDto<?> nickCheck(@RequestBody MemberRequestDto requestDto){
        return memberService.isNickCheck(requestDto);
    }

}

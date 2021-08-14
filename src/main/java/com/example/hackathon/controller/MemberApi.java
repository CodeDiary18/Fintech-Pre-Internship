package com.example.hackathon.controller;

import com.example.hackathon.dto.MemberDto;
import com.example.hackathon.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberApi {
    private final MemberService memberService;

    @PostMapping("/login")
    public String login(@RequestBody MemberDto loginDto) {
        if (memberService.login(loginDto.getEmail(), loginDto.getPassword()) == false) {
            return "로그인 실패";
        } else {
            return "로그인 성공";
        }
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody MemberDto memberDto) {
        boolean result = memberService.signUp(memberDto);
        if (result == false) {  // 이미 존재하는 사용자
            return "이미 존재하는 사용자";
        } else {   // 중복된 사용자 없음
            return "가입 성공";
        }
    }
}

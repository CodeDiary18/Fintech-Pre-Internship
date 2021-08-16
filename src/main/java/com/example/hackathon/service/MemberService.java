package com.example.hackathon.service;

import com.example.hackathon.dto.MemberDto;
import com.example.hackathon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public boolean login(String id, String password) {  // 로그인
        if (memberRepository.findByUserEmailAndUserPw(id, password) == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean signUp(MemberDto memberDto) {   // 사용자 추가
        if (memberRepository.findByUserEmail(memberDto.getEmail()) == null) {
            memberRepository.save(memberDto.toEntity());
            return true;
        } else {
            return false;
        }
    }

}

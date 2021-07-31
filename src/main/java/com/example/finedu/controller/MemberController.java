package com.example.finedu.controller;

import com.example.finedu.entity.Member;
import com.example.finedu.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    final MemberRepository memberRepository;

    @GetMapping("/{key}")
    public Member getMember(@PathVariable("key") Long key){
        return memberRepository.findById(key).orElse(null);
    }

}

package com.example.finedu.service;

import com.example.finedu.entity.Member;
import com.example.finedu.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    final MemberRepository memberRepository;

    public String greet(String name) {
        return String.format("Welcome, %s", name);
    }

    public Member findById(Long key) {
        return memberRepository.findById(key).orElse(null);
    }

    public Member findMember(Long key, String name) {
        return memberRepository.findBySeqAndName(key, name).orElse(null);
    }

    public Member addUser(Member member) {
        return memberRepository.save(member);
    }

    public List<Object> countOrgGroup(Boolean isActive) {
        return memberRepository.countOrgGroup(isActive);
    }
}
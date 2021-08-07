package com.example.finedu.controller;

import com.example.finedu.entity.Member;
import com.example.finedu.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    final MemberService memberService;

    @GetMapping("/info")
    public Map<String, Object> getUserInfo(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }

    @PostMapping("/save")
    public void saveMember(@RequestBody Member member) {
        memberService.addUser(member);
    }

    @GetMapping("/{key}")
    public Member getMember(@PathVariable("key") Long key,
                            @RequestParam(required = false) String name) {
        if (name != null) {
            return memberService.findMember(key, name);
        } else {
            return memberService.findMember(key);
        }
    }

    @GetMapping("/api/count")
    public List<Object> countByOrgGroup(@RequestParam Boolean isActive) {
        return memberService.countOrgGroup(isActive);
    }
}

package com.example.hackathon.controller;

import com.example.hackathon.dto.LoanDto;
import com.example.hackathon.entity.UserInfo;
import com.example.hackathon.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class LoanController {
    private final LoanService loanService;

    @PostMapping("/loan-apply")
    public String loan(@AuthenticationPrincipal UserInfo userInfo, LoanDto loanDto) { // 대출신청
        loanDto.setUserId(userInfo.getSeq());
//        System.out.println(loanDto.toString());
        loanService.apply(loanDto);
        return "redirect:/";
    }

}



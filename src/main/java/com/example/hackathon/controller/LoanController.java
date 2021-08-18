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
        if (loanService.preHistoryCheck(userInfo.getSeq()) == false) { // 이전 대출 신청 처리 확인
            System.out.println("아직 처리되지 않은 대출 신청건 존재");
        } else {
            loanDto.setUserId(userInfo.getSeq());
            loanService.apply(loanDto); // 대출 신청
        }
        return "redirect:/";
    }

}



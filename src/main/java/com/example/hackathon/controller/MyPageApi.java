package com.example.hackathon.controller;

import com.example.hackathon.entity.LoanModel;
import com.example.hackathon.entity.UserInfo;
import com.example.hackathon.service.InvestService;
import com.example.hackathon.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageApi {
    private final InvestService investService;
    private final LoanService loanService;

    @GetMapping("/loan")
    public String loanList(@AuthenticationPrincipal UserInfo userInfo, Model model){
        List<LoanModel> loans = loanService.findUserLoans(userInfo.getSeq());
        model.addAttribute("loans",loans);
        return "mypage/loanList";
    }
}

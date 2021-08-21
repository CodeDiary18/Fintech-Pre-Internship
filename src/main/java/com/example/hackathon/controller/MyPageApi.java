package com.example.hackathon.controller;

import com.example.hackathon.dto.ApprovedLoanDto;
import com.example.hackathon.entity.ApprovedLoanModel;
import com.example.hackathon.entity.InvestModel;
import com.example.hackathon.entity.LoanModel;
import com.example.hackathon.entity.UserInfo;
import com.example.hackathon.service.ApprovedLoanService;
import com.example.hackathon.service.InvestService;
import com.example.hackathon.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageApi {
    private final InvestService investService;
    private final LoanService loanService;
    private final ApprovedLoanService approvedLoanService;

    @GetMapping("/loan")
    public String loanList(@AuthenticationPrincipal UserInfo userInfo, Model model) {
        List<LoanModel> loans = loanService.findUserLoans(userInfo.getSeq());
        model.addAttribute("loans", loans);
        return "mypage/loanList";
    }

    @GetMapping("/invest")
    public String investList(@AuthenticationPrincipal UserInfo userInfo, Model model) {
        List<InvestModel> invests = investService.findAllInvested(userInfo.getSeq());
        List<ApprovedLoanModel> approvedLoanModels = approvedLoanService.findMyPageApproved(invests);
        List<LoanModel> loanModels = loanService.findMyPageLoan(approvedLoanModels);
        model.addAttribute("invests", invests);
        model.addAttribute("approveds", approvedLoanModels);
        model.addAttribute("loans", loanModels);
        // 다른거 끌고 오기
        return "mypage/investList";
    }


    @PostMapping("/repay/{id}")
    public String repay(@PathVariable("id") Long loan_id, ApprovedLoanDto approvedLoanDto) {

        approvedLoanService.updateRepayment(loan_id, approvedLoanDto.getRepayment());

        return "redirect:/mypage/loan";
    }
}

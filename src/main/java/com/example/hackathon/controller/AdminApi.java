package com.example.hackathon.controller;

import com.example.hackathon.entity.LoanModel;
import com.example.hackathon.service.ApprovedLoanService;
import com.example.hackathon.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminApi { // 관리자 api

    private final LoanService loanService;
    private final ApprovedLoanService approvedLoanService;

    @GetMapping("/loan-list")
    public String loanList(Model model){
        List<LoanModel> loans = loanService.findLoans()
                .stream()
                .filter(loan -> loan.getCrawlValid()!=0) //crawled 진행된 대출만 노출
                .collect(Collectors.toList());
        model.addAttribute("loans",loans);
        return "admin/loanList";
    }

    @PostMapping("/loan-approve")
    public String approveLoan(@RequestParam Long loan_id, String action) {
        if (action.equals("approve")) {
            loanService.updatePermit(loan_id, 1);
            //approvedLoan 생성
            approvedLoanService.apply(loan_id);
        } else if (action.equals("reject")) {
            loanService.updatePermit(loan_id, -1);
        }
        return "redirect:loan-list";
    }
}

package com.example.hackathon.controller;

import com.example.hackathon.entity.LoanModel;
import com.example.hackathon.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminApi { // 관리자 api

    private final LoanService loanService;

    @GetMapping("/loan-list")
    public String loanList(Model model){
        List<LoanModel> loans = loanService.findLoans();
        model.addAttribute("loans",loans);
        return "admin/loanList";
    }

    @PostMapping("/loan-approve")
    public String approveLoan(@RequestParam Long loan_id, String action) {
        if (action.equals("approve")) {
            loanService.updatePermit(loan_id, 1);
        } else if (action.equals("reject")) {
            loanService.updatePermit(loan_id, -1);
        }
        return "redirect:loan-list";
    }
}

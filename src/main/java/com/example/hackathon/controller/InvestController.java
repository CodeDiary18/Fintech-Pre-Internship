package com.example.hackathon.controller;

import com.example.hackathon.entity.LoanModel;
import com.example.hackathon.service.InvestService;
import com.example.hackathon.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class InvestController {
    private final InvestService investService;
    private final LoanService loanService;

    @Autowired
    public InvestController(InvestService investService, LoanService loanService) {
        this.investService = investService;
        this.loanService = loanService;
    }

    @GetMapping("/invest")
    public String investList(Model model) {
        List<LoanModel> loans = loanService.findLoans()
                .stream()
                .filter(loan->loan.getPermit()==1)
                .collect(Collectors.toList());

        model.addAttribute("loans",loans);
        return "invest";
    }
}

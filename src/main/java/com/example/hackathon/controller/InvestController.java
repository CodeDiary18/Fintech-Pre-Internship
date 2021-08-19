package com.example.hackathon.controller;

import com.example.hackathon.dto.InvestDto;
import com.example.hackathon.entity.LoanModel;
import com.example.hackathon.entity.UserInfo;
import com.example.hackathon.service.Crawl;
import com.example.hackathon.service.InvestService;
import com.example.hackathon.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class InvestController {
    private final InvestService investService;
    private final LoanService loanService;
    private final Crawl crawl;
    @Autowired
    public InvestController(InvestService investService, LoanService loanService, Crawl crawl) {
        this.investService = investService;
        this.loanService = loanService;
        this.crawl = crawl;
    }

    @GetMapping("/invest")
    public String investList(Model model) {
        List<LoanModel> loans = loanService.findLoans()
                .stream()
                .filter(loan->loan.getPermit()==1) //승인된 대출만 노출
                .collect(Collectors.toList());

        model.addAttribute("loans",loans);
        return "invest";
    }

    @GetMapping("/invest-details/{id}")
    public String investOn(@PathVariable("id") Long loan_id, Model model) {
        model.addAttribute("loan",loanService.findLoan(loan_id));
        model.addAttribute("crawled", crawl.findCrawled(loan_id));
        return "invest/investDetails";
    }
    @PostMapping("/invest-details/{id}")
    public String invest(@PathVariable("id") Long loan_id,
                         @AuthenticationPrincipal UserInfo userInfo,
                         InvestDto investDto) {
        investDto.setInvId(loan_id);
        investDto.setUserId(userInfo.getSeq());
        investService.apply(investDto);
        return "redirect:/";
    }
}

package com.example.hackathon.controller;

import com.example.hackathon.entity.LoanModel;
import com.example.hackathon.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminApi { // 관리자 api

    private final LoanService loanService;

    @GetMapping("/loanList")
    public String loanList(Model model){
        List<LoanModel> loans = loanService.findLoans();
        model.addAttribute("loans",loans);
        return "admin/loanList";
    }
}

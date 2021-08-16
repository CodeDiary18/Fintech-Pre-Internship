package com.example.hackathon.controller;

import com.example.hackathon.service.InvestModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class InvestModelController {
    private final InvestModelService investModelService;

    @Autowired
    public InvestModelController(InvestModelService investModelService) {
        this.investModelService = investModelService;
    }


}

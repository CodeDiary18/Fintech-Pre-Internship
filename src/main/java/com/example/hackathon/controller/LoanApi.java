package com.example.hackathon.controller;

import com.example.hackathon.dto.LoanDto;
import com.example.hackathon.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/loan")
@RequiredArgsConstructor
public class LoanApi {
    private final LoanService loanService;

    @PostMapping("/predict")
    public String predict(@Valid @RequestBody LoanDto loanDto) {    // 대출 한도 조회
        Long estimated_amount = loanService.predict(loanDto);
        return "당신의 대출 한도 : " + estimated_amount.toString();
    }

    @PostMapping("/apply")
    public String apply(@Valid @RequestBody LoanDto loanDto) {  // 대출 신청
        loanDto.setLoanAt(LocalDateTime.now());
        if(loanService.relatedAgencyCheck(loanDto)==false){
            return "관련 정보 부족";
        }
        if (loanService.apply(loanDto) == true) {
            return "대출 신청 성공";
        } else {
            return "대출 신청 오류로 인한실패";
        }
    }
}

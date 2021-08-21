package com.example.hackathon.controller;

import com.example.hackathon.dto.InvestDto;
import com.example.hackathon.entity.ApprovedLoanModel;
import com.example.hackathon.entity.LoanModel;
import com.example.hackathon.entity.UserInfo;
import com.example.hackathon.service.ApprovedLoanService;
import com.example.hackathon.service.Crawl;
import com.example.hackathon.service.InvestService;
import com.example.hackathon.service.LoanService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class InvestController {
    private final InvestService investService;
    private final LoanService loanService;
    private final Crawl crawl;
    private final ApprovedLoanService approvedLoanService;


    @GetMapping("/invest")
    public String investList(Model model) {
        List<ApprovedLoanModel> loans = approvedLoanService.findLoans();
        model.addAttribute("approvedLoans", loans);
        return "invest";
    }

    @GetMapping("/invest-details/{id}")
    public String investOn(@PathVariable("id") Long seq, Model model) {
        Long loan_id = approvedLoanService.findBySeq(seq).getLoanId();
        model.addAttribute("loan", loanService.findLoan(loan_id));
        model.addAttribute("approvedLoan", approvedLoanService.findBySeq(seq));
        model.addAttribute("crawled", crawl.findCrawled(loan_id));
        return "invest/investDetails";
    }

    @PostMapping("/invest-details/{id}")
    public String invest(@PathVariable("id") Long seq,
                         @AuthenticationPrincipal UserInfo userInfo,
                         InvestDto investDto, HttpServletResponse response) throws IOException {
        ApprovedLoanModel temp = approvedLoanService.findBySeq(seq);
        LoanModel loan = loanService.findLoan(temp.getLoanId());

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (userInfo.getSeq() == loan.getUserId()) {
            out.println("<script>alert('사용자가 신청한 대출상품입니다.'); history.go(-1);</script>");
        } else if (userInfo.getBalance() < investDto.getInvAmount()) { // 사용자 잔액 부족
            out.println("<script>alert('사용자 잔액이 부족합니다.'); history.go(-1);</script>");
        } else if (investDto.getInvAmount() <= loan.getLoanAmount() - temp.getCollectedAmount()) {
            //투자객체 생성
            investDto.setInvId(temp.getLoanId());
            investDto.setUserId(userInfo.getSeq());
            investService.apply(investDto);
            //승인된 대출 모인금액 갱신
            approvedLoanService.updateCollectedAmount(seq, investDto.getInvAmount());
            out.println("<script>alert('투자가 성공적으로 처리되었습니다.'); location.href='/mypage'</script>");
        } else {//투자금액 초과
            out.println("<script>alert('투자 금액을 초과했습니다.'); history.go(-1);</script>");
        }
        out.flush();
        return "";
//        return "redirect:/invest-details/{id}";
    }
}

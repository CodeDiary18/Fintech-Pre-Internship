package com.example.hackathon.controller;

import com.example.hackathon.dto.ApprovedLoanDto;
import com.example.hackathon.dto.UserInfoDto;
import com.example.hackathon.entity.*;
import com.example.hackathon.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageApi {
    private final InvestService investService;
    private final LoanService loanService;
    private final ApprovedLoanService approvedLoanService;
    private final MyPageService myPageService;
    private final UserService userService;

    @GetMapping("/loan")
    public String loanList(@AuthenticationPrincipal UserInfo userInfo, Model model) {

        List<MyLoanedModel> loans = myPageService.extendLoan(
                loanService.findUserLoans(userInfo.getSeq())
                        .stream()
                        .filter(loan -> loan.getPermit() == 1)
                        .collect(Collectors.toList())
        );
        model.addAttribute("loans", loans);
        return "mypage/loanList";
    }

    @GetMapping("/invest")
    public String investList(@AuthenticationPrincipal UserInfo userInfo, Model model){

        List<MyInvestedModel> invests = myPageService.extendInvest(investService.findAllInvested(userInfo.getSeq()));
        model.addAttribute("invests", invests);

        return "mypage/investList";
    }

    @GetMapping("/balance")
    public String balance(@AuthenticationPrincipal UserInfo userInfo, Model model) {
        UserInfo user = userService.findUser(userInfo.getSeq());
        model.addAttribute("userInfo", user);
        return "mypage/balance";
    }

    @PostMapping("/fill-balance")
    public String fillBalance(UserInfoDto userInfoDto,
                              @AuthenticationPrincipal UserInfo userInfo,
                              HttpServletResponse response) throws IOException{
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        //잔액 증가
        userService.updateBalance(userInfo.getSeq(),userInfoDto.getBalance(),"+");
        out.println("<script>alert('충전이 성공적으로 처리되었습니다.'); location.href='/mypage/balance'</script>");
        out.flush();
        return "";
    }
    @PostMapping("/repay/{id}")
    public String repay(@PathVariable("id") Long loan_id, ApprovedLoanDto approvedLoanDto,
                        HttpServletResponse response) throws IOException {

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 1:모인금액 범위 안에서 상환 완료, 0:상환할 금액이 없음, -1:범위 초과해서 상환함
        int result = approvedLoanService.updateRepayment(loan_id, approvedLoanDto);
        switch (result) {
            case 1:
                out.println("<script>alert('상환이 성공적으로 처리되었습니다.'); location.href='/mypage'</script>");
                break;
            case 0:
                out.println("<script>alert('상환할 금액이 없습니다.'); history.go(-1);</script>");
                break;
            case -1:
                out.println("<script>alert('모인 금액을 초과한 값입니다.'); history.go(-1);</script>");
                break;
        }
        out.flush();
        return "";
        //return "redirect:/mypage/loan";
    }
}

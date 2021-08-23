package com.example.hackathon.controller;

import com.example.hackathon.dto.LoanDto;
import com.example.hackathon.entity.LoanModel;
import com.example.hackathon.entity.UserInfo;
import com.example.hackathon.service.Crawl;
import com.example.hackathon.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
@Controller
public class LoanController {
    private final LoanService loanService;
    private final Crawl crawl;

/*    @PostMapping("/loan-apply")
    public String loan(@AuthenticationPrincipal UserInfo userInfo, LoanDto loanDto) throws IOException { // 대출신청
        if (loanService.preHistoryCheck(userInfo.getSeq()) == false) { // 이전 대출 신청 처리 확인
            System.out.println("아직 처리되지 않은 대출 신청건 존재");
        } else {
            loanDto.setUserId(userInfo.getSeq());
            LoanModel temp = loanService.apply(loanDto); // 대출 신청
            //크롤링 진행
            crawl.crawl(temp.getSeq(), loanDto.getChannelName(), loanDto.getChannelAddress());
        }
        return "redirect:/";
    }*/

    @PostMapping("/loan-apply")
    public String loan(@AuthenticationPrincipal UserInfo userInfo, @Valid LoanDto loanDto,
                       BindingResult bindingResult, HttpServletResponse response) throws IOException { // 대출신청
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (bindingResult.hasErrors()) {
            out.println("<script>alert('정보를 제대로 입력해주세요'); history.go(-1);</script>");
        } else if (loanService.preHistoryCheck(userInfo.getSeq()) == false) { // 이전 대출 신청 처리 확인
            out.println("<script>alert('아직 처리되지 않은 대출 신청건이 존재합니다.'); location.href ='/';</script>");
        } else {
            loanDto.setUserId(userInfo.getSeq());
            LoanModel temp = loanService.apply(loanDto);
            if (temp == null) { // 대출 신청
                out.println("<script>alert('정보를 모두 입력해주세요'); history.go(-1);</script>");
            } else {
                out.println("<script>alert('대출 신청이 정상적으로 처리되었습니다.'); location.href ='/';</script>");
                crawl.crawl(temp.getSeq(), loanDto.getChannelName(), loanDto.getChannelAddress(), false, 0L);
            }
        }
        out.flush();
        return "";
    }
}



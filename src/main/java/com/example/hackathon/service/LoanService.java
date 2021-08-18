package com.example.hackathon.service;

import com.example.hackathon.dto.LoanDto;
import com.example.hackathon.repository.LoanModelRepository;
import com.example.hackathon.user.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoanService {
    private final LoanModelRepository loanModelRepository;

    public boolean apply(LoanDto loanDto) {    // 대출 신청
        System.out.println(loanDto.toString());
        try {
            if (loanDto.isAgency() == true) {   // 개인
                loanDto.setBusinessNumber(null);
                loanDto.setBusinessName(null);
            } else {
                loanDto.setCompanyName(null);
                loanDto.setCompanyPayday(null);
            }
            loanModelRepository.save(loanDto.toEntity());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean relatedAgencyCheck(LoanDto loanDto) {
        if (loanDto.isAgency() == false) {  // 개인
            if (loanDto.getBusinessNumber() == null | loanDto.getBusinessName() == null) {
                return false;
            }
        } else {    // 소속
            if (loanDto.getCompanyName() == null | loanDto.getCompanyPayday() == null) {
                return false;
            }
        }
        return true;
    }

    public LoanDto lookUp(String userId) {       // 대출 신청 한거 확인  -> but, 나중에 없애야 할 듯
        LoanDto loanDto = new LoanDto(loanModelRepository.findByUserId(userId));
        return loanDto;
    }

    public Long predict(LoanDto loanDto) {  // 예상 대출 가능 금액
        return 5000000L;
    }
}

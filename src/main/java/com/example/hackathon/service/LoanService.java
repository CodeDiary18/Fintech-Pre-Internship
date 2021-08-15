package com.example.hackathon.service;

import com.example.hackathon.dto.LoanDto;
import com.example.hackathon.repository.LoanModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoanService {
    private final LoanModelRepository loanModelRepository;

    public boolean apply(LoanDto loanDto) {    // 대출 신청
        try {
            loanModelRepository.save(loanDto.toEntity());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public LoanDto lookUp(String userId){       // 대출 신청 한거 확인  -> but, 나중에 없애야 할 듯
        LoanDto loanDto = new LoanDto(loanModelRepository.findByUserId(userId));
        return loanDto;
    }

    public Long predict(LoanDto loanDto) {  // 예상 대출 가능 금액
        return 1000L;
    }
}

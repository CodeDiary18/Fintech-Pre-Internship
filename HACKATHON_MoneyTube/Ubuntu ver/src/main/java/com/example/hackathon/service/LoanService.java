package com.example.hackathon.service;

import com.example.hackathon.dto.LoanDto;
import com.example.hackathon.entity.ApprovedLoanModel;
import com.example.hackathon.entity.LoanModel;
import com.example.hackathon.repository.LoanModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoanService {
    private final LoanModelRepository loanModelRepository;

    public LoanModel apply(LoanDto loanDto) {    // 대출 신청
        System.out.println(loanDto.toString());
        try {
            if (loanDto.isAgency() == true) {   // 개인
                if (loanDto.getCompanyName() == null | loanDto.getCompanyPayday() == null) {
                    return null;
                }
                loanDto.setBusinessNumber(null);
                loanDto.setBusinessName(null);
            } else {
                if (loanDto.getBusinessName() == null | loanDto.getBusinessNumber() == null) {
                    return null;
                }
                loanDto.setCompanyName(null);
                loanDto.setCompanyPayday(null);
            }
            return loanModelRepository.save(loanDto.toEntity());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
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

    public boolean preHistoryCheck(Long userId) {
        LoanModel loanModel = loanModelRepository.findFirstByUserIdOrderByLoanAtDesc(userId);
        if (loanModel == null) {    // 대출 이력이 전혀 없는 경우
            return true;
        } else {
            LoanDto loanDto = new LoanDto(loanModel);
            if (loanDto.getPermit() == 0) {
                return false;
            }
        }
        return true;
    }

    public LoanDto lookUp(Long userId) {       // 대출 신청 한거 확인  -> but, 나중에 없애야 할 듯
        LoanDto loanDto = new LoanDto(loanModelRepository.findByUserId(userId));
        return loanDto;
    }

    public Long predict(LoanDto loanDto) {  // 예상 대출 가능 금액
        return 5000000L;
    }

    public List<LoanModel> findLoans() {
        return loanModelRepository.findAll();
    }

    public List<LoanModel> findUserLoans(Long userId) {
        return loanModelRepository.findAllByUserIdOrderByLoanAtDesc(userId);
    }

    public void updatePermit(Long loan_id, int permit) {
        LoanModel temp = loanModelRepository.findById(loan_id).orElseThrow();
        temp.setPermit(permit);
        loanModelRepository.save(temp);
    }

    public LoanModel findLoan(Long loan_id) {
        return loanModelRepository.findById(loan_id).orElseThrow();
    }

    public List<LoanModel> findMyPageLoan(List<ApprovedLoanModel> approvedLoanModels) {
        List<LoanModel> loanModels = new ArrayList<>();
        for (ApprovedLoanModel approvedLoanModel : approvedLoanModels) {
//            investModel.getInvId(); // 투자한 객체 번호
            LoanModel temp = loanModelRepository.findBySeq(approvedLoanModel.getLoanId());
            loanModels.add(temp);
        }
        return loanModels;
    }
}

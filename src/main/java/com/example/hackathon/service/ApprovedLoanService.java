package com.example.hackathon.service;

import com.example.hackathon.dto.ApprovedLoanDto;
import com.example.hackathon.entity.ApprovedLoanModel;
import com.example.hackathon.entity.InvestModel;
import com.example.hackathon.repository.ApprovedLoanModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApprovedLoanService {
    private final ApprovedLoanModelRepository approvedLoanModelRepository;

    public ApprovedLoanModel apply(Long loan_id) {
        ApprovedLoanDto approvedLoanDto = new ApprovedLoanDto();
        approvedLoanDto.setLoanId(loan_id);
        return approvedLoanModelRepository.save(approvedLoanDto.toEntity());
    }

    public List<ApprovedLoanModel> findLoans() {
        return approvedLoanModelRepository.findAll();
    }

    public ApprovedLoanModel findBySeq(Long seq) {
        return approvedLoanModelRepository.findById(seq).orElseThrow();
    }

    public void updateCollectedAmount(Long seq, Long invAmount) {
        ApprovedLoanModel temp = approvedLoanModelRepository.findById(seq).orElseThrow();
        temp.setCollectedAmount(temp.getCollectedAmount() + invAmount);
        approvedLoanModelRepository.save(temp);
    }

    public void updateRepayment(Long loan_id, Long repayment) {
        ApprovedLoanModel temp = approvedLoanModelRepository.findByLoanId(loan_id);
        temp.setRepayment(temp.getRepayment() + repayment);
        approvedLoanModelRepository.save(temp);
    }

    public List<ApprovedLoanModel> findMyPageApproved(List<InvestModel> invests) {
        List<ApprovedLoanModel> approvedLoanModels = new ArrayList<>();
        for (InvestModel investModel : invests) {
//            investModel.getInvId(); // 투자한 객체 번호
            ApprovedLoanModel temp = approvedLoanModelRepository.findBySeq(investModel.getInvId());
            approvedLoanModels.add(temp);
        }
        return approvedLoanModels;
    }
}

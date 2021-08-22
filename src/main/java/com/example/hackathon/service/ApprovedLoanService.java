package com.example.hackathon.service;

import com.example.hackathon.dto.ApprovedLoanDto;
import com.example.hackathon.entity.ApprovedLoanModel;
import com.example.hackathon.entity.InvestModel;
import com.example.hackathon.repository.ApprovedLoanModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
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
        return approvedLoanModelRepository.findAll(Sort.by(Sort.Direction.DESC, "seq"));
    }

    public ApprovedLoanModel findBySeq(Long seq) {
        return approvedLoanModelRepository.findById(seq).orElseThrow();
    }

    public void updateCollectedAmount(Long seq, Long invAmount) {
        ApprovedLoanModel temp = approvedLoanModelRepository.findById(seq).orElseThrow();
        temp.setCollectedAmount(temp.getCollectedAmount() + invAmount);
        approvedLoanModelRepository.save(temp);
    }

    public int updateRepayment(Long loan_id, ApprovedLoanDto approvedLoanDto) {


        Long repayment = approvedLoanDto.getRepayment();
        ApprovedLoanModel temp = approvedLoanModelRepository.findByLoanId(loan_id);

        if(temp.getCollectedAmount() >= temp.getRepayment()+repayment) {
            //모인금액 범위 안에서 상환 완료
            temp.setRepayment(temp.getRepayment() + repayment);
            approvedLoanModelRepository.save(temp);
            return 1;
        }
        else if(temp.getCollectedAmount() == 0){
            //상환할 금액이 없음
            return 0;
        }
        else{
            //범위 초과해서 상환함
            return -1;
        }


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

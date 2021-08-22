package com.example.hackathon.service;

import com.example.hackathon.entity.ApprovedLoanModel;
import com.example.hackathon.entity.LoanModel;
import com.example.hackathon.entity.TotalInvestModel;
import com.example.hackathon.repository.ApprovedLoanModelRepository;
import com.example.hackathon.repository.LoanModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TotalInvestService {
    private final ApprovedLoanModelRepository approvedLoanModelRepository;
    private final LoanModelRepository loanModelRepository;


    public List<TotalInvestModel> extend(List<ApprovedLoanModel> approvedLoanModels) {
        List<TotalInvestModel> loans = new ArrayList<>();
        for (ApprovedLoanModel approvedLoanModel : approvedLoanModels) {
            Long loan_id = approvedLoanModel.getLoanId();

            TotalInvestModel totalInvestModel = new TotalInvestModel(approvedLoanModel.getSeq(),
                    approvedLoanModel.getCollectedAmount(),
                    approvedLoanModel.getRepayment());
            LoanModel loanModel = loanModelRepository.findById(loan_id).orElseThrow();
            totalInvestModel.setBusinessNumber(loanModel.getBusinessNumber());
            totalInvestModel.setBusinessName(loanModel.getBusinessName());
            totalInvestModel.setCompanyName(loanModel.getCompanyName());
            totalInvestModel.setCompanyPayday(loanModel.getCompanyPayday());
            totalInvestModel.setChannelAddress(loanModel.getChannelAddress());
            totalInvestModel.setChannelName(loanModel.getChannelName());
            totalInvestModel.setCategory(loanModel.getCategory());
            totalInvestModel.setReasonForLoan(loanModel.getReasonForLoan());
            totalInvestModel.setLoanAmount(loanModel.getLoanAmount());
            totalInvestModel.setLoanAt(loanModel.getLoanAt());
            totalInvestModel.setRepayAt(loanModel.getRepayAt());

            loans.add(totalInvestModel);
        }

        return loans;

    }
}

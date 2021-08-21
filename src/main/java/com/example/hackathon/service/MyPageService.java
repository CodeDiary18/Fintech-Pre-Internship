package com.example.hackathon.service;

import com.example.hackathon.entity.ApprovedLoanModel;
import com.example.hackathon.entity.InvestModel;
import com.example.hackathon.entity.LoanModel;
import com.example.hackathon.entity.MyInvestedModel;
import com.example.hackathon.repository.ApprovedLoanModelRepository;
import com.example.hackathon.repository.InvestModelRepository;
import com.example.hackathon.repository.LoanModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyPageService {

    private final InvestModelRepository investModelRepository;
    private final ApprovedLoanModelRepository approvedLoanModelRepository;
    private final LoanModelRepository loanModelRepository;

    public List<MyInvestedModel> extend(List<InvestModel> myInvests) {

        List<MyInvestedModel> invests = new ArrayList<>();
        for (InvestModel myInvest : myInvests) {

            Long loan_id = myInvest.getInvId(); //신청한 대출객체 고유번호

            //invest 필드 세팅
            MyInvestedModel myInvestedModel = new MyInvestedModel(myInvest.getSeq(), myInvest.getUserId(),
                    myInvest.getInvId(), myInvest.getInvAmount(),myInvest.getInvestAt());
            //approved loan 필드 세팅
            ApprovedLoanModel approvedLoanModel = approvedLoanModelRepository.findByLoanId(loan_id);
            myInvestedModel.setCollectedAmount(approvedLoanModel.getCollectedAmount());
            myInvestedModel.setRepayment(approvedLoanModel.getRepayment());
            //loan 필드 세팅
            LoanModel loanModel = loanModelRepository.findBySeq(loan_id);
            myInvestedModel.setBusinessNumber(loanModel.getBusinessNumber());
            myInvestedModel.setBusinessName(loanModel.getBusinessName());
            myInvestedModel.setCompanyName(loanModel.getCompanyName());
            myInvestedModel.setCompanyPayday(loanModel.getCompanyPayday());
            myInvestedModel.setChannelAddress(loanModel.getChannelAddress());
            myInvestedModel.setChannelName(loanModel.getChannelName());
            myInvestedModel.setCategory(loanModel.getCategory());
            myInvestedModel.setReasonForLoan(loanModel.getReasonForLoan());
            myInvestedModel.setLoanAmount(loanModel.getLoanAmount());
            myInvestedModel.setLoanAt(loanModel.getLoanAt());
            myInvestedModel.setRepayAt(loanModel.getRepayAt());

            invests.add(myInvestedModel);
        }
        return invests;
    }
}

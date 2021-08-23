package com.example.hackathon.service;

import com.example.hackathon.entity.*;
import com.example.hackathon.repository.ApprovedLoanModelRepository;
import com.example.hackathon.repository.InvestModelRepository;
import com.example.hackathon.repository.LoanModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class MyPageService {

    private final InvestModelRepository investModelRepository;
    private final ApprovedLoanModelRepository approvedLoanModelRepository;
    private final LoanModelRepository loanModelRepository;


    public List<MyLoanedModel> extendLoan(List<LoanModel> myLoans) {

        List<MyLoanedModel> loans = new ArrayList<>();
        for (LoanModel myLoan : myLoans) {

            Long loan_id = myLoan.getSeq();

            //loan 필드 세팅
            MyLoanedModel myLoanedModel = new MyLoanedModel(myLoan.getSeq()
                    ,myLoan.getBusinessName(), myLoan.getBusinessName(),
                    myLoan.getCompanyName(),myLoan.getCompanyPayday(),
                    myLoan.getChannelAddress(),myLoan.getChannelName(),myLoan.getCategory(),
                    myLoan.getReasonForLoan(),myLoan.getLoanAmount(),myLoan.getLoanAt(),myLoan.getRepayAt(), myLoan.getPermit());

            //approved 필드 세팅
            ApprovedLoanModel approvedLoanModel = approvedLoanModelRepository.findByLoanId(loan_id);
            myLoanedModel.setCollectedAmount(approvedLoanModel.getCollectedAmount());
            myLoanedModel.setRepayment(approvedLoanModel.getRepayment());

            loans.add(myLoanedModel);
        }
        return loans;

    }


    public List<MyInvestedModel> extendInvest(List<InvestModel> myInvests) {

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

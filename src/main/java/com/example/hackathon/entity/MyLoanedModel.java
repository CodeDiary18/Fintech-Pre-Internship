package com.example.hackathon.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
public class MyLoanedModel {
    private Long seq;
    //private boolean agency;
    private String businessNumber;
    private String businessName;
    private String companyName;
    private String companyPayday;
    private String channelAddress;
    private String channelName;
    private String category;
    private String reasonForLoan;
    private Long loanAmount;
    private LocalDateTime loanAt;
    private Date repayAt;
    private int permit;

    private Long collectedAmount;
    private Long repayment;


    public MyLoanedModel(Long seq, String businessNumber,
                         String businessName, String companyName, String companyPayday,
                         String channelAddress, String channelName, String category,
                         String reasonForLoan, Long loanAmount, LocalDateTime loanAt, Date repayAt, int permit) {
        this.seq = seq;
        //this.agency = agency;
        this.businessNumber = businessNumber;
        this.businessName = businessName;
        this.companyName = companyName;
        this.companyPayday = companyPayday;
        this.channelAddress = channelAddress;
        this.channelName = channelName;
        this.category = category;
        this.reasonForLoan = reasonForLoan;
        this.loanAmount = loanAmount;
        this.loanAt = loanAt;
        this.repayAt = repayAt;
        this.permit = permit;
    }
}

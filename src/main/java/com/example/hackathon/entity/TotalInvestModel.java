package com.example.hackathon.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
public class TotalInvestModel {
    private Long seq;
    private Long collectedAmount;
    private Long repayment;

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

    public TotalInvestModel(Long seq, Long collectedAmount, Long repayment) {
        this.seq = seq;
        this.collectedAmount = collectedAmount;
        this.repayment = repayment;
    }
}

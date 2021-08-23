package com.example.hackathon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
public class MyInvestedModel {

    private Long seq;
    private Long userId;
    private Long invId;
    private Long invAmount;
    private LocalDateTime investAt;

    private Long collectedAmount;
    private Long repayment;

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

    public MyInvestedModel(Long seq, Long userId, Long invId, Long invAmount, LocalDateTime investAt) {
        this.seq = seq;
        this.userId = userId;
        this.invId = invId;
        this.invAmount = invAmount;
        this.investAt = investAt;
    }
}

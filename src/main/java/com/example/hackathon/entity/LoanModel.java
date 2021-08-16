package com.example.hackathon.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class LoanModel {
    @Id
    @GeneratedValue
    private Long seq;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private boolean agency;

    private String businessNumber;

    private String businessName;

    private String companyName;

    private String companyPayday;

    @Column(nullable = false)
    private String channelAddress;

//    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String reasonForLoan;

    @Column(nullable = false, updatable = false)
    private LocalDateTime loanAt;

    @ColumnDefault("0")
    private int permit;
}
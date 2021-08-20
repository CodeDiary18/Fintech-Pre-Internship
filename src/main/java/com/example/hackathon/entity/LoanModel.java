package com.example.hackathon.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Date;
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
  
    @Column
    private Long userId;

    @Column(nullable = false)
    private boolean agency;

    private String businessNumber;

    private String businessName;

    private String companyName;

    private String companyPayday;

    private String channelAddress;

    private String channelName;

    //    @Enumerated(EnumType.STRING)
    private String category;

    private String reasonForLoan;

    private Long loanAmount;

    @Column(updatable = false)
    private LocalDateTime loanAt;

    private Date repayAt;

    @ColumnDefault("0")
    private int permit;

    @ColumnDefault("0")
    private int crawlValid;

    @PrePersist
    public void prePersist() {
        this.loanAt = LocalDateTime.now();
    }
}
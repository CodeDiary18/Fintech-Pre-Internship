package com.example.hackathon.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
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

    @Column(unique = true)
    private Long userId;

    @Column(nullable = false)
    private boolean agency;

    private String businessNumber;

    private String businessName;

    private String companyName;

    private String companyPayday;

    private String channelAddress;

    //    @Enumerated(EnumType.STRING)
    private String category;

    private String reasonForLoan;

    @Column(updatable = false)
    private LocalDateTime loanAt;

    @ColumnDefault("0")
    private int permit;

    @PrePersist
    public void prePersist() {
        this.loanAt = LocalDateTime.now();
    }
}
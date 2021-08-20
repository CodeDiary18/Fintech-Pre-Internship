package com.example.hackathon.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ApprovedLoanModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private Long loanId;
    private Long collectedAmount;
    private Long repayment;

    @PrePersist
    public void prePersist() {
        this.collectedAmount = this.collectedAmount == null ? 0 : this.collectedAmount;
        this.repayment = this.repayment == null ? 0 : this.repayment;
    }
}

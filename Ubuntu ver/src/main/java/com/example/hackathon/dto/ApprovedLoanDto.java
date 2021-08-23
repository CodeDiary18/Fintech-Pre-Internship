package com.example.hackathon.dto;

import com.example.hackathon.entity.ApprovedLoanModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApprovedLoanDto {
    @NotNull
    private Long loanId;
    private Long collectedAmount;
    private Long repayment;

    public ApprovedLoanModel toEntity() {
        return ApprovedLoanModel.builder()
                .seq(null)
                .loanId(loanId)
                .collectedAmount(collectedAmount)
                .repayment(repayment)
                .build();
    }

    public ApprovedLoanDto(ApprovedLoanModel approvedLoanModel) {
        this.loanId = approvedLoanModel.getLoanId();
        this.collectedAmount = approvedLoanModel.getCollectedAmount();
        this.repayment = approvedLoanModel.getRepayment();
    }
}

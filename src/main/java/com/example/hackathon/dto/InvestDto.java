package com.example.hackathon.dto;

import com.example.hackathon.entity.InvestModel;
import com.example.hackathon.entity.LoanModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestDto {

    @NotNull
    private Long userId;
    @NotNull
    private Long invId;

    private Long invAmount;

    private LocalDateTime investAt;

    public InvestModel toEntity() {
        return InvestModel.builder()
                .seq(null)
                .userId(userId)
                .invId(invId)
                .invAmount(invAmount)
                .investAt(investAt)
                .build();
    }

    public InvestDto(InvestModel investModel) {
        this.userId = investModel.getUserId();
        this.invId = investModel.getInvId();
        this.invAmount = investModel.getInvAmount();
        this.investAt = investModel.getInvestAt();
    }
}

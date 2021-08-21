package com.example.hackathon.dto;

import com.example.hackathon.entity.InvestModel;
import com.example.hackathon.entity.LoanModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MypageInvestDto {

    private Long userId;

    private Long invId;

    private Long invAmount;

    private LocalDateTime investAt;

    private LoanModel loanModel;


}

package com.example.hackathon.dto;

import com.example.hackathon.entity.LoanModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDto {
    @NotNull
    private Long userId;
    @NotNull
    private boolean agency;
//    @Pattern(regexp = "^\\d{3}-\\d{2}-\\d{5}$", message = "xxx-xx-xxxxx 형식으로 입력")
    private String businessNumber;
    private String businessName;
    private String companyName;
    private String companyPayday;
    private String channelAddress;
    private String channelName;
    private String loanCategory;
    @Size(min = 10)
    private String reasonForLoan;
    private Long loanAmount;
    private LocalDateTime loanAt;
    private int permit;
    private int crawlValid;

    public LoanModel toEntity() {   // dto -> entity
        return LoanModel.builder()
                .seq(null)
                .userId(userId)
                .agency(agency)
                .businessNumber(businessNumber)
                .businessName(businessName)
                .companyName(companyName)
                .companyPayday(companyPayday)
                .channelAddress(channelAddress)
                .channelName(channelName)
                .category(loanCategory)
                .reasonForLoan(reasonForLoan)
                .loanAmount(loanAmount)
                .loanAt(loanAt)
                .permit(permit)
                .crawlValid(crawlValid)
                .build();
    }

    public LoanDto(LoanModel loanModel) {   // entity -> dto
        this.userId = loanModel.getUserId();
        this.agency = loanModel.isAgency();
        this.businessNumber = loanModel.getBusinessNumber();
        this.businessName = loanModel.getBusinessName();
        this.companyName = loanModel.getCompanyName();
        this.companyPayday = loanModel.getCompanyPayday();
        this.channelAddress = loanModel.getChannelAddress();
        this.channelName = loanModel.getChannelName();
        this.loanCategory = loanModel.getCategory();
        this.reasonForLoan = loanModel.getReasonForLoan();
        this.loanAmount = loanModel.getLoanAmount();
        this.loanAt = loanModel.getLoanAt();
        this.permit = loanModel.getPermit();
        this.crawlValid = loanModel.getCrawlValid();
    }

}

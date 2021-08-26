package com.example.hackathon.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
public class UserInfoDto {
    @Email
    private String email;
    private String password;
    private String auth;
    @Pattern(regexp = "^[가-힣]*$", message = "올바르게 이름을 입력해주세요.")
    private String name;

    @Pattern(regexp = "\\d{2}([0]\\d|[1][0-2])([0][1-9]|[1-2]\\d|[3][0-1])[-]*[1-4]\\d{6}",
            message = "올바르게 주민번호를 입력해주세요.")
    private String socialNumber;

    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$",
            message = "올바르게 핸드폰 번호를 입력해주세요. ex) 010-1234-5678")
    private String mobileNumber;

    private String account;
    private LocalDateTime signAt;
    private Long balance;
}
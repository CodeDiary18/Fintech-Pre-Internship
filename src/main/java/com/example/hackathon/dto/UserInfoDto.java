package com.example.hackathon.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.sql.Date;


@Getter
@Setter
public class UserInfoDto {
    @Email
    private String email;
    private String password;
    private String auth;
    @Pattern(regexp = "^[가-힣]*$")
    private String name;
    @Pattern(regexp = "\\d{2}([0]\\d|[1][0-2])([0][1-9]|[1-2]\\d|[3][0-1])[-]*[1-4]\\d{6}")
    private String socialNumber;
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$")
    private String mobileNumber;
    private String account;
    private Date signAt;
    private Long balance;
}

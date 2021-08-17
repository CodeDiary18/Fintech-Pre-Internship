package com.example.hackathon.user;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
public class UserInfoDto {
    private String email;
    private String password;
    private String auth;
    private String name;
    private String socialNumber;
    private String mobileNumber;
    private String account;
    private Date signAt;
    private Long balance;
}

package com.example.hackathon.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @Temporal(TemporalType.DATE)
    private Date signAt;
    private Long balance;
}

package com.example.hackathon.dto;

import com.example.hackathon.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private String name;
    private String socialNumber;
    private String mobileNumber;
    @Pattern(regexp = "^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$")
    private String email;
    private String password;
    private String account;


    public Member toEntity() {   // dto -> entity
        return Member.builder()
                .seq(null)
                .name(name)
                .socialNumber(socialNumber)
                .mobileNumber(mobileNumber)
                .userEmail(email)
                .userPw(password)
                .account(account)
                .build();
    }

    public MemberDto(Member member) {   // entity -> dto
        this.name = member.getName();
        this.socialNumber = member.getSocialNumber();
        this.mobileNumber = member.getMobileNumber();
        this.email = member.getUserEmail();
        this.password = member.getUserPw();
        this.account = member.getAccount();
    }

}

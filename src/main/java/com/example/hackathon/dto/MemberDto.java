package com.example.hackathon.dto;

import com.example.hackathon.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private String id;
    private String password;
    private String name;

    public MemberEntity toEntity() {   // dto -> entity
        return MemberEntity.builder()
                .seq(null)
                .userId(id)
                .userPw(password)
                .userName(name).build();
    }

    public MemberDto(MemberEntity memberEntity) {// entity -> dto
        this.id = memberEntity.getUserId();
        this.password = memberEntity.getUserPw();
        this.name = memberEntity.getUserName();
    }

}

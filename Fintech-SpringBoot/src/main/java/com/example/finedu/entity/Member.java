package com.example.finedu.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Member {
    //PostgreSQL(pgAdmin)에서 Databases>postgres>Schema>public>Tables에서 확인 가능
    @Id
    @GeneratedValue
    Long seq;
    String id;
    String name;
    String org;
    Boolean active;
}

package com.example.hackathon.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Nonnull
public class InvestModel {

    @Id
    @GeneratedValue
    private Long seq;

    private Long userId;

    private Long invId;

    private Long invAmount;

    private Date investAt;

}

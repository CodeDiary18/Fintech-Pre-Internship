package com.example.hackathon.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class CrawledModel {
    @Id
    @GeneratedValue
    private Long seq;
    private Long loanId;
    private Integer positiveNum;
    private Integer negativeNum;
    private Integer neutralNum;
    private Long rank;
    private Long subscriber;
    private Long view;
    private Long avgView;
    private Long video;
    private Long liveIncome;
    private String noxScore;

}

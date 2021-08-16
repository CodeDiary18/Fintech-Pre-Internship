package com.example.hackathon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class investModelDto {


    private Long invId;
    private Long invAmount;
    private Date investAt;


}

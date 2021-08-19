package com.example.hackathon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comment {
    private String word;
    private Integer times;
}

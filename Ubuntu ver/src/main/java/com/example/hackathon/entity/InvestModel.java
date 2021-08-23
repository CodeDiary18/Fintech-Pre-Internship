package com.example.hackathon.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class InvestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private Long userId;

    private Long invId;

    private Long invAmount;

    @Column(updatable = false)
    private LocalDateTime investAt;


    @PrePersist
    public void prePersist() {
        this.investAt = LocalDateTime.now();
    }
}

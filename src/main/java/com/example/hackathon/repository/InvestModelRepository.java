package com.example.hackathon.repository;

import com.example.hackathon.entity.InvestModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InvestModelRepository extends JpaRepository<InvestModel, Long> {
    List<InvestModel> findByUserIdOrderByInvestAtDesc(Long userId);
}

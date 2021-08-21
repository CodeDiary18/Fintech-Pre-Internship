package com.example.hackathon.repository;

import com.example.hackathon.entity.InvestModel;
import com.example.hackathon.entity.LoanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvestModelRepository extends JpaRepository<InvestModel, Long> {
    List<InvestModel> findByUserIdOrderByInvestAtDesc(Long userId);
}

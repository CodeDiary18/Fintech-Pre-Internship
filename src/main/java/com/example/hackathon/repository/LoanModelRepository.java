package com.example.hackathon.repository;

import com.example.hackathon.dto.LoanDto;
import com.example.hackathon.entity.LoanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanModelRepository extends JpaRepository<LoanModel, Long> {
    LoanModel findByUserId(String userId);
}

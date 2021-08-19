package com.example.hackathon.repository;

import com.example.hackathon.entity.LoanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanModelRepository extends JpaRepository<LoanModel, Long> {
    List<LoanModel> findAllByUserIdOrderByLoanAtDesc(Long userId);

    LoanModel findByUserId(Long userId);

    LoanModel findFirstByUserIdOrderByLoanAtDesc(Long userId);
}

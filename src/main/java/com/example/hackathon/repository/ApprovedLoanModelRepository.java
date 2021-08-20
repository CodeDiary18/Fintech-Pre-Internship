package com.example.hackathon.repository;

import com.example.hackathon.entity.ApprovedLoanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApprovedLoanModelRepository extends JpaRepository<ApprovedLoanModel, Long> {
}

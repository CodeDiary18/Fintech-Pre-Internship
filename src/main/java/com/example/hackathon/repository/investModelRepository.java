package com.example.hackathon.repository;

import com.example.hackathon.entity.investModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface investModelRepository extends JpaRepository<investModel, Long> {
    investModel findByUserId(Long userId);
}

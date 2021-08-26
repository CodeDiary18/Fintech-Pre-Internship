package com.example.hackathon.repository;

import com.example.hackathon.dto.CrawledDto;
import com.example.hackathon.entity.CrawledModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawledModelRepository extends JpaRepository<CrawledModel,Long> {
    CrawledModel findByLoanId(Long loanId);
}

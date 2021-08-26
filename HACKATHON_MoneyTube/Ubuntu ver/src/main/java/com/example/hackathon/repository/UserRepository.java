package com.example.hackathon.repository;

import com.example.hackathon.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByEmail(String email);

    Optional<UserInfo> findBySocialNumber(String socialNumber);
}

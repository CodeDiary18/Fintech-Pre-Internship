package com.example.hackathon.repository;

import com.example.hackathon.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    //seq, name, socialNumber, mobileNumber, userEmail, userPw, account;
    Member findByUserEmail(String userEmail);

    Member findByUserEmailAndUserPw(String userEmail, String userPw);
}

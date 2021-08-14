package com.example.hackathon.repository;

import com.example.hackathon.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //seq, userId, userPw, userName;
    MemberEntity findByUserId(String userId);

    MemberEntity findByUserIdAndUserPw(String userId, String userPw);
}

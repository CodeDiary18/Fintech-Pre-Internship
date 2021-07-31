package com.example.finedu.repository;

import com.example.finedu.entity.Member;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {    //members라는 api 자동 생성
}

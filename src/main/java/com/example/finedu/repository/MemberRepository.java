package com.example.finedu.repository;

import com.example.finedu.entity.Member;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {
    // build.gradle에 "implementation "org.springframework.boot:spring-boot-starter-data-rest" 이것 때문에
    // members라는 api 자동 생성
}

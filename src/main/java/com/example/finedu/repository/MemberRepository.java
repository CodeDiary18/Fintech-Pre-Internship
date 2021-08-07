package com.example.finedu.repository;

import com.example.finedu.entity.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {
    // build.gradle에 "implementation "org.springframework.boot:spring-boot-starter-data-rest" 이것 때문에
    // members라는 api 자동 생성

    Optional<Member> findBySeqAndName(Long key, String name);

    @Query("SELECT m.org, count(m.seq) FROM Member m where m.active = ?1 group by m.org")
    List<Object> countOrgGroup(Boolean isActive);
}

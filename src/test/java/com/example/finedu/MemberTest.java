package com.example.finedu;

import com.example.finedu.entity.Member;
import com.example.finedu.repository.MemberRepository;
import com.example.finedu.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class MemberTest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void join() throws Exception{
        // Given : Member 빌더로 멤버 객체 생성 후
        Member member = new Member();
        member.setName("winter");
        member.setId("elsa");
        member.setOrg("sm");
        member.setActive(true);

        // When : 멤머가 가입되면 (MemberService, 스프링컨텍스트가 사용된걸 확인)
        Member storedMember = memberService.addUser(member);

        // Then : 그 멤버를 다시 찾을 때 둘이 이름이 같아야 함
        Member foundMember = memberRepository.findById(storedMember.getSeq()).get();
        assertEquals(member.getName(), foundMember.getName());
    }
}

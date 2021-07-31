package com.example.finedu.controller;

import com.example.finedu.entity.Hello;
import com.example.finedu.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor    //@Autowired와 constructor 대신
public class HelloRestController {
    //@Autowired ->Field Injection

    final MemberService memberService;  //이 방법 권장
/*
    @Autowired
    public HelloRestController(MemberService memberService) {
        this.memberService = memberService;
    }
*/

/*
    @Autowired
    private void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
*/

    @GetMapping("/hello-string")
    public String helloString(String name) {
        //return String.format("Hello, %s", name);
        return memberService.greet(name);
    }

    @GetMapping("/hello-map")
    public Map<String, Object> helloMap(String name) {
        Map<String, Object> m = new HashMap<>();
        m.put("name", name);
        return m;
    }

    @GetMapping("/hello-object")
    public Hello helloObject(String name) {
        Hello h = new Hello(name);
        return h;
    }
}

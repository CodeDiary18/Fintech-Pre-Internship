package com.example.finedu.controller;

import com.example.finedu.entity.Member;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class Main {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Member builtMember = new Member("myId", "myname", "koscom");
        //Member.builder().id("myid").name("myname").org("koscom").build();

        // 직렬화
        String json = mapper.writeValueAsString(builtMember);
        byte[] byteStream = json.getBytes(); // 네트워크 전송용
        System.out.println(json);

        log.debug("Debugging message");

        // 역직렬화
        String inputString = new String(byteStream);
        Member readObject = mapper.readValue(inputString, Member.class);
        System.out.println(readObject);

        Map<String,Object> readMap = mapper.readValue(inputString, Map.class);
        System.out.println(readMap);
    }

}

package com.example.hackathon.service;

import com.example.hackathon.entity.InvestModel;
import com.example.hackathon.entity.Member;
import com.example.hackathon.repository.InvestModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InvestModelService {
    private final InvestModelRepository investModelRepository;

    @Autowired
    public InvestModelService(InvestModelRepository investModelRepository) {
        this.investModelRepository = investModelRepository;
    }

    //잔액에서 투자금액 빼는 로직


    //로그인한? 멤버의 seq로 투자한 객체들 탐색
    public List<InvestModel> findAllInvested(Member member) {
       return investModelRepository.findByUserId(member.getSeq());
    }



}

package com.example.hackathon.service;

import com.example.hackathon.entity.InvestModel;
import com.example.hackathon.repository.InvestModelRepository;
import com.example.hackathon.entity.UserInfo;
import com.example.hackathon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InvestModelService {
    private final InvestModelRepository investModelRepository;
    private final UserRepository userRepository;

    @Autowired
    public InvestModelService(InvestModelRepository investModelRepository, UserRepository userRepository) {
        this.investModelRepository = investModelRepository;
        this.userRepository = userRepository;
    }

    //투자정보 생성
    public void save(InvestModel investModel, @AuthenticationPrincipal UserInfo userInfo) {
        userRepository.getById(userInfo.getSeq()).setBalance(userInfo.getBalance()-investModel.getInvAmount());
        //investModel.save();
    }

    //로그인한 회원이 투자한 객체 모두 찾기
    public List<InvestModel> findAllInvested(@AuthenticationPrincipal UserInfo userInfo) {
       return investModelRepository.findByUserId(userInfo.getSeq());
    }



}

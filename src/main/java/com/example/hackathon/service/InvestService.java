package com.example.hackathon.service;

import com.example.hackathon.dto.InvestDto;
import com.example.hackathon.entity.InvestModel;
import com.example.hackathon.repository.InvestModelRepository;
import com.example.hackathon.entity.UserInfo;
import com.example.hackathon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InvestService {
    private final InvestModelRepository investModelRepository;
    private final UserRepository userRepository;

    @Autowired
    public InvestService(InvestModelRepository investModelRepository, UserRepository userRepository) {
        this.investModelRepository = investModelRepository;
        this.userRepository = userRepository;
    }

    //투자정보 생성
    public void apply(InvestDto investDto) {
        investModelRepository.save(investDto.toEntity());
    }

    //로그인한 회원이 투자한 객체 모두 찾기
    public List<InvestModel> findAllInvested(Long userId) {
       return investModelRepository.findByUserIdOrderByInvestAtDesc(userId);
    }
}

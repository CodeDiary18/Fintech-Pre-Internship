package com.example.hackathon.service;

import com.example.hackathon.controller.LoanController;
import com.example.hackathon.entity.CrawledModel;
import com.example.hackathon.entity.LoanModel;
import com.example.hackathon.repository.CrawledModelRepository;
import com.example.hackathon.repository.LoanModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CrawlSchedule {
    private final Crawl crawl;
    private final CrawledModelRepository crawledModelRepository;
    private final LoanModelRepository loanModelRepository;

    @Scheduled(fixedDelay = 600000)
    public void crawlSchedule() throws IOException {
        List<CrawledModel> crawledModels = crawledModelRepository.findAll();
        for(CrawledModel crawledModel:crawledModels){
            LoanModel loanModel = loanModelRepository.findById(crawledModel.getLoanId()).orElseThrow();
            crawl.crawl(crawledModel.getLoanId(), loanModel.getChannelName(), loanModel.getChannelAddress(),
                    true, crawledModel.getSeq());
        }
    }


}

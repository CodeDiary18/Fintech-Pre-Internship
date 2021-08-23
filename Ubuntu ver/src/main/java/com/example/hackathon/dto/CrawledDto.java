package com.example.hackathon.dto;

import com.example.hackathon.entity.CrawledModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrawledDto {
    private Long loanId;
    private Integer positiveNum;
    private Integer negativeNum;
    private Integer neutralNum;
    private Long rank;
    private Long subscriber;
    private Long view;
    private Long avgView;
    private Long video;
    private Long liveIncome;
    private String noxScore;

    public CrawledModel toEntity() {
        return CrawledModel.builder()
                .seq(null)
                .loanId(loanId)
                .positiveNum(positiveNum)
                .negativeNum(negativeNum)
                .neutralNum(neutralNum)
                .rank(rank)
                .subscriber(subscriber)
                .view(view)
                .avgView(avgView)
                .video(video)
                .liveIncome(liveIncome)
                .noxScore(noxScore)
                .build();
    }

    public CrawledDto(CrawledModel crawledModel) {
        this.loanId = crawledModel.getLoanId();
        this.positiveNum = crawledModel.getPositiveNum();
        this.negativeNum = crawledModel.getNegativeNum();
        this.neutralNum = crawledModel.getNeutralNum();
        this.rank = crawledModel.getRank();
        this.subscriber = crawledModel.getSubscriber();
        this.view = crawledModel.getView();
        this.avgView = crawledModel.getAvgView();
        this.video = crawledModel.getVideo();
        this.liveIncome = crawledModel.getLiveIncome();
        this.noxScore = crawledModel.getNoxScore();
    }
}

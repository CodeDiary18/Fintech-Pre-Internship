package com.example.hackathon.service;
import com.example.hackathon.dto.CrawledDto;
import com.example.hackathon.repository.CrawledModelRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Crawl {

    private final CrawledModelRepository crawledModelRepository;

    @Autowired
    public Crawl(CrawledModelRepository crawledModelRepository) {
        this.crawledModelRepository = crawledModelRepository;
    }

    private static Integer stringToInt(String in){

        if(in.contains("건"))
            in = in.replace("건", "");
        if(in.contains(","))
            in = in.replace(",", "");
        return Integer.parseInt(in);
    }
    private static Long stringToLong(String in){

        Double result;
        if(in.contains(("￦")))
            in = in.replace("￦", "").trim();
        if(in.equals("-"))
            return null;
        if(in.contains("만")){
            result = Double.parseDouble(in.replace("만", "")) * 10000;
            return result.longValue();
        }
        if (in.contains("억")) {
            result = Double.parseDouble(in.replace("억", "")) * 100000000;
            return result.longValue();
        }
        if(in.contains("nd")){
            return Long.parseLong(in.replace("nd",""));
        }
        if(in.contains("th")){
            return Long.parseLong(in.replace("th",""));
        }
        return Long.parseLong(in);
    }
    public void crawl(Long loan_id, String find, String channel) throws IOException {

        CrawledDto crawledDto = new CrawledDto();
        crawledDto.setLoanId(loan_id);

        String URL = "https://some.co.kr/analysis/social/reputation";
        String filePath = "C:\\Tools\\chromedriver.exe";

        try {
            System.setProperty("webdriver.chrome.driver", filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        WebDriver driver = new ChromeDriver(options);
        driver.get(URL);
        driver.findElement(By.xpath("/html/body/div[13]/div[5]/div/p/a")).click();
        driver.findElement(By.xpath("//button[@type='button']")).click();
        try {Thread.sleep(1000);} catch (InterruptedException e) {}
        driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/div[2]/div[1]/form/fieldset/div/div[1]/div/input")).sendKeys(find);
        driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/div[2]/div[2]/div/div[2]/button")).click();
        try {Thread.sleep(6000);} catch (InterruptedException e) {}

        Document html = Jsoup.connect(URL).get();

        Elements p = html.select("span.legend-value");
        crawledDto.setPositiveNum(stringToInt(p.get(0).text()));
        crawledDto.setNegativeNum(stringToInt(p.get(1).text()));
        crawledDto.setNeutralNum(stringToInt(p.get(2).text()));

        URL = "https://kr.noxinfluencer.com/youtube/channel/" + channel;
        driver.get(URL);
        try {Thread.sleep(3000);} catch (InterruptedException e) {}
        driver.findElement(By.xpath("//*[@id=\"tab-channel\"]/div[1]/div/div[1]/div[1]/div/ul/li[2]/a")).click();
        html = Jsoup.connect(URL).get();

        //지역 랭킹
        crawledDto.setRank(stringToLong(html.select("#area-rank > a").text()));

        //구독자, 조회수, 평균조회수, 동영상, 라이브수입
        Elements baseData = html.select("ul.base-data").select("span.strong");
        crawledDto.setSubscriber(stringToLong(baseData.get(0).text()));
        crawledDto.setView(stringToLong(baseData.get(1).text()));
        crawledDto.setAvgView(stringToLong(baseData.get(2).text()));
        crawledDto.setVideo(stringToLong(baseData.get(3).text()));
        crawledDto.setLiveIncome(stringToLong(baseData.get(4).text()));

        //nox 보고서
        crawledDto.setNoxScore(html.select("div.noxscore-content span.score").text().strip());

        driver.close();
        crawledModelRepository.save(crawledDto.toEntity());
        System.out.println(crawledDto.toString());
    }
}

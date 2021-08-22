package com.example.hackathon;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // 요청 - 뷰 연결
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
//       registry.addViewController("/admin").setViewName("admin");
//        registry.addViewController("/signup").setViewName("signup");
        registry.addViewController("/mypage").setViewName("mypage");
        registry.addViewController("/loan").setViewName("loan");
       // registry.addViewController("/invest").setViewName("invest");
    }
}
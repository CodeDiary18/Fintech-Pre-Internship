package com.example.finedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello-mvc")
    public String helloTemplate(String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }
}
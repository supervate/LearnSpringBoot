package com.learn.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class controllers {

    @GetMapping("*.jsp")
    public String jsp(HttpServletRequest request){
        return "jsp";
    }
    @GetMapping("c1")
    public String c1(){
        int c =1;
        return "1";
    }
    @GetMapping("c2")
    public String c2(){
        int c =2;
        return "2";
    }
}

// src/main/java/com/gdg/spring_study/controller/HelloController.java

package com.gdg.spring_study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 이 클래스가 RESTful API 컨트롤러임을 선언한다.
public class HelloController {

    @GetMapping("/hello") // HTTP GET 요청이 "/hello" 경로로 들어올 때 이 메서드를 실행한다.
    public String hello() {
        return "Hello, World!";
    }
}
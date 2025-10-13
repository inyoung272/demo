package com.demo.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 컨트롤러 어노테이션 명시

public class DemoController {

    @GetMapping("/about_detailed")
    public String showAboutDetailed() {
        // "about_detailed"는 templates/about_detailed.html 파일을 가리킵니다.
        return "about_detailed";
    }
}
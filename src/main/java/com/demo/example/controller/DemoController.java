package com.demo.example.controller;

import com.model.service.TestService; // TestService의 실제 import 경로로 가정
import com.model.domain.TestDB; // TestDB의 실제 import 경로로 가정
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;
import java.util.List; // List 타입을 위해 import 추가

@Controller
@RequiredArgsConstructor // TestService 주입을 위해 Lombok 생성자 자동 생성
public class DemoController {

    // [수정됨] 1. @Autowired와 필드 주입을 제거합니다.
    // [수정됨] 2. final 키워드를 사용하여 생성자 주입을 유도합니다.
    private final TestService testService;

    @GetMapping("/about_detailed")
    public String showAboutDetailed() {
        return "about_detailed";
    }

    @GetMapping("/test1")
    public String thymeleaf_test1(Model model) {
        model.addAttribute("data1", "<h2> 방갑습니다 </h2>");
        model.addAttribute("data2", "태그의 속성 값");
        model.addAttribute("link", 01);
        model.addAttribute("name", "최인영");
        model.addAttribute("para1", "001");
        model.addAttribute("para2", 002);
        return "thymeleaf_test1";
    }

    // 메인 페이지 매핑
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // DemoController.java 파일의 getAllTestDBs 메서드 수정본

    // DemoController.java 파일의 getAllTestDBs 메서드 수정

    @GetMapping("/testdb")
    public String getAllTestDBs(Model model) {

        // [최종 수정] 1. name: 키워드를 제거하여 Java 문법을 준수합니다.
        List<TestDB> results = testService.findByName("최인영");

        TestDB test = null;
        String status = "DB 연결 성공! 하지만 '최인영' 데이터가 없습니다.";

        if (!results.isEmpty()) {
            test = results.get(0);
            status = "'최인영' 데이터 조회 성공!";
        }

        model.addAttribute("dbStatus", status);
        model.addAttribute("data4", test);

        System.out.println("데이터 출력 디버그 : " + test);
        return "testdb";
    }
}
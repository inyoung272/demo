package com.demo.example.controller;

import com.model.domain.Article; // Article 엔티티 import
import com.model.service.BlogService; // BlogService import
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;

import java.util.List; // List 타입 사용을 위한 import

@Controller // 이 클래스를 Spring Bean으로 등록하고 컨트롤러로 사용하도록 지정
@RequiredArgsConstructor // final 필드에 대한 생성자 주입을 자동으로 처리 (BlogService 주입)
public class BlogController {

    // BlogService를 주입받음 (게시글 데이터를 가져오기 위해 필수)
    private final BlogService blogService;

    @GetMapping("/article_list") // URL 경로를 /article_list로 지정
    public String article_list(Model model) {

        // 1. Service를 통해 게시글 전체 목록(List<Article>)을 조회
        List<Article> articles = blogService.findAll();

        // 2. 조회된 리스트를 "articles"라는 이름으로 HTML 뷰(Model)에 추가
        model.addAttribute("articles", articles);

        // 3. templates/article_list.html 파일을 찾아 렌더링하도록 반환
        return "article_list";
    }
}
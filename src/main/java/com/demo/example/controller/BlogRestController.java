package com.demo.example.controller;

import com.model.domain.Article;
import com.model.service.AddArticleRequest; // 이 클래스는 사용자님의 프로젝트에 있어야 합니다.
import com.model.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController // @Controller + @ResponseBody
public class BlogRestController {

    // [수정됨] 1. 필드 선언 시 타입과 변수 이름 사이에 공백 추가
    // [수정됨] 2. final 키워드를 사용하여 생성자 주입의 대상임을 명확히 함
    private final BlogService blogService;

    // @PostMapping("/api/articles") // post 요청
    // [수정됨] 3. @ModelAttribute 대신 @RequestBody 사용 권장 (REST API 표준)
    // [수정됨] 4. 매개변수 타입과 변수 이름 사이에 공백 추가
    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {

        // request 객체를 사용하여 블로그 서비스에 저장
        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED) // HTTP 201 Created 반환
                .body(savedArticle);
    }

    @GetMapping("/favicon.ico")
    public void favicon() {
    }
}
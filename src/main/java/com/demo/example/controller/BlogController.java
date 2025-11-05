package com.demo.example.controller;

import com.model.domain.Article; // Article 엔티티 import
import com.model.domain.Board;
import com.model.service.AddArticleRequest;
import com.model.service.BlogService; // BlogService import
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import lombok.RequiredArgsConstructor;

import java.util.List; // List 타입 사용을 위한 import
import java.util.Optional;

@Controller // 이 클래스를 Spring Bean으로 등록하고 컨트롤러로 사용하도록 지정
@RequiredArgsConstructor // final 필드에 대한 생성자 주입을 자동으로 처리 (BlogService 주입)
public class BlogController {

    // BlogService를 주입받음 (게시글 데이터를 가져오기 위해 필수)
    private final BlogService blogService;

    // @GetMapping("/article_list") // URL 경로를 /article_list로 지정
    // public String article_list(Model model) {

    // // 1. Service를 통해 게시글 전체 목록(List<Article>)을 조회
    // List<Article> articles = blogService.findAll();

    // // 2. 조회된 리스트를 "articles"라는 이름으로 HTML 뷰(Model)에 추가
    // model.addAttribute("articles", articles);

    // // 3. templates/article_list.html 파일을 찾아 렌더링하도록 반환
    // return "article_list";
    // }

    @GetMapping("/board_list") // 새로운 게시판 링크 지정
    public String board_list(Model model) {
        List<Board> list = blogService.findAll(); // 게시판 전체 리스트, 기존 Article에서 Board로 변경됨
        model.addAttribute("boards", list); // 모델에 추가
        return "board_list"; // .HTML 연결
    }

    @GetMapping("/board_view/{id}") // 게시판 링크 지정
    public String board_view(Model model, @PathVariable Long id) {
        Optional<Board> list = blogService.findById(id); // 선택한 게시판 글
        if (list.isPresent()) {
            model.addAttribute("boards", list.get()); // 존재할 경우 실제 Board 객체를 모델에 추가
        } else {
            // 처리할 로직 추가 (예: 오류 페이지로 리다이렉트, 예외 처리 등)
            return "/error_page/article_error"; // 오류 처리 페이지로 연결
        }
        return "board_view"; // .HTML 연결
    }

    @PostMapping("/articles")
    public String addArticle(@ModelAttribute AddArticleRequest request) {
        blogService.save(request);
        return "redirect:/article_list";
    }

    // @GetMapping("/article_edit/{id}") // 게시판 링크 지정
    // public String article_edit(Model model, @PathVariable Long id) {
    // Optional<Article> list = blogService.findById(id); // 선택한 게시판 글
    // if (list.isPresent()) {
    // model.addAttribute("article", list.get()); // 존재하면 Article 객체를 모델에 추가
    // } else {
    // // 처리할 로직 추가 (예: 오류 페이지로 리다이렉트, 예외 처리 등)
    // return "error"; // 오류 처리 페이지로 연결
    // }
    // return "article_edit"; // .HTML 연결
    // }

    @PutMapping("/api/article_edit/{id}")
    public String updateArticle(@PathVariable Long id, @ModelAttribute AddArticleRequest request) {
        blogService.update(id, request);
        return "redirect:/article_list"; // 글 수정 이후 .html 연결
    }

    @DeleteMapping("/api/article_delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        blogService.delete(id);
        return "redirect:/article_list";
    }

}
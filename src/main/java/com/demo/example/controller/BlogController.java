package com.demo.example.controller;

import com.model.domain.Board;
import com.model.service.AddArticleRequest;
import com.model.service.BlogService; // BlogService import

import jakarta.servlet.http.HttpSession;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    // @GetMapping("/board_list") // 새로운 게시판 링크 지정
    // public String board_list(Model model) {
    // List<Board> list = blogService.findAll(); // 게시판 전체 리스트, 기존 Article에서 Board로
    // 변경됨
    // model.addAttribute("boards", list); // 모델에 추가
    // return "board_list"; // .HTML 연결
    // }

    @GetMapping("/board_list") // 새로운 게시판 링크 지정
    public String board_list(Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String keyword, HttpSession session) {
        String userId = (String) session.getAttribute("userId"); // 세션 아이디 존재 확인
        String email = (String) session.getAttribute("email");
        if (userId == null) {
            return "redirect:/login"; // 로그인 페이지로 리다이렉션
        }
        System.out.println("세션 userId: " + userId); // 서버 IDE 터미널에 세션 값 출력

        PageRequest pageable = PageRequest.of(page, 3); // 한 페이지의 게시글 수
        Page<Board> list; // Page를 반환

        if (keyword.isEmpty()) {
            list = blogService.findAll(pageable); // 기본 전체 출력(키워드 x)
        } else {
            list = blogService.searchByKeyword(keyword, pageable); // 키워드로 검색
        }

        int startNum = (page * 3) + 1;
        model.addAttribute("startNum", startNum);

        model.addAttribute("boards", list); // 모델에 추가
        model.addAttribute("totalPages", list.getTotalPages()); // 페이지 크기
        model.addAttribute("currentPage", page); // 페이지 번호
        model.addAttribute("keyword", keyword); // 키워드
        model.addAttribute("email", email); // 로그인 사용자(이메일)
        return "board_list"; // .HTML 연결
    }

    @GetMapping("/board_view/{id}")
    public String board_view(Model model, @PathVariable Long id, HttpSession session) {
        Optional<Board> list = blogService.findById(id);

        if (list.isPresent()) {
            model.addAttribute("boards", list.get());

            String loginUser = (String) session.getAttribute("name");
            model.addAttribute("loginUser", loginUser);

        } else {
            return "/error_page/article_error";
        }
        return "board_view";
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

    @PutMapping("/api/board_edit/{id}")
    public String updateBoard(@PathVariable Long id, @ModelAttribute AddArticleRequest request) {
        blogService.update(id, request);
        return "redirect:/board_list";
    }

    @GetMapping("/board_edit/{id}")
    public String board_edit(@PathVariable Long id, Model model) {
        // 1. 수정할 글 하나를 가져옵니다.
        Optional<Board> board = blogService.findById(id);

        // 2. 글이 있으면 화면에 전달합니다.
        if (board.isPresent()) {
            model.addAttribute("board", board.get());

            // (선택) 하단에 목록도 같이 보여주려면 추가
            List<Board> list = blogService.findAll();
            model.addAttribute("boards", list);

            return "board_edit"; // templates/board_edit.html 파일을 엽니다.
        } else {
            // 글이 없으면 에러 페이지로
            return "/errorPage";
        }
    }

    @DeleteMapping("/api/board_delete/{id}")
    public String deleteBoard(@PathVariable Long id) {
        blogService.delete(id);
        return "redirect:/board_list";
    }

    // 글쓰기 게시판
    @GetMapping("/board_write")
    public String board_write() {
        return "board_write";
    }

    @PostMapping("/api/boards")
    public String addboards(@ModelAttribute AddArticleRequest request, HttpSession session) {

        String userName = (String) session.getAttribute("name");

        // 3. 만약 로그인이 안 되어 있다면(null이면), "익명"으로 처리하거나 로그인 페이지로 보냅니다.
        if (userName == null) {
            userName = "anonymous"; // 또는 return "redirect:/login";
        }

        // 4. DTO(request)에 작성자 정보를 강제로 넣어줍니다.
        request.setUser(userName);

        blogService.save(request);
        return "redirect:/board_list";
    }

    // 생략…

}
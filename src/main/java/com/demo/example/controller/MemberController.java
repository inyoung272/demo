package com.demo.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

// 필요한 다른 패키지 import (Service, DTO 등)
import com.model.service.MemberService;
import com.model.domain.Member;
import com.model.service.AddMemberRequest;
import lombok.RequiredArgsConstructor;

@Controller // 1. 이 어노테이션이 있어야 컨트롤러로 동작합니다.
@RequiredArgsConstructor // 2. memberService를 자동으로 주입받기 위해 필요합니다.
public class MemberController { // 3. 반드시 클래스 선언이 있어야 합니다!

    // 4. 코드 하단에서 사용하는 memberService 변수 선언
    private final MemberService memberService;

    @GetMapping("/join_new") // 회원 가입 페이지 연결
    public String join_new() {
        return "join_new"; // .HTML 연결
    }

    @PostMapping("/api/members") // 회원 가입 저장
    public String addmembers(@ModelAttribute AddMemberRequest request) {
        memberService.saveMember(request);
        return "join_end"; // .HTML 연결
    }

    @GetMapping("/login") // 로그인 페이지 연결
    public String login() {
        return "login"; // .HTML 연결
    }

    @PostMapping("/api/login_check") // 로그인(아이디, 패스워드) 체크
    public String checkMembers(@ModelAttribute AddMemberRequest request, Model model) {
        try {

            Member member = memberService.loginCheck(request.getEmail(), request.getPassword()); // 패스워드 반환
            model.addAttribute("member", member); // 로그인 성공 시 회원 정보 전달
            return "redirect:/board_list"; // 로그인 성공 후 이동할 페이지
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage()); // 에러 메시지 전달
            return "login"; // 로그인 실패 시 로그인 페이지로 리다이렉트
        }
    }
}
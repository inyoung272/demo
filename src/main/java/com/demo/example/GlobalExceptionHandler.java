package com.demo.example;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice // 이 클래스가 전역 예외 처리를 담당
public class GlobalExceptionHandler {

    // MethodArgumentTypeMismatchException 예외를 감지
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatch(MethodArgumentTypeMismatchException ex, Model model) {

        // 슬라이드의 에러 페이지에 맞는 메시지 전달
        model.addAttribute("title", "잘못된 접근(문자열 변환 에러)");
        model.addAttribute("message1", "잘못된 게시물입니다!");
        model.addAttribute("message2", "요청하신 게시글을 찾을 수 없거나, 접근 권한이 없습니다.");

        // 'errorPage.html' 템플릿을 반환
        return "errorPage";
    }
}
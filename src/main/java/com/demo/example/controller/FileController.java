package com.demo.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// [중요] java.nio.file.Path를 사용해야 합니다. (JPA Path 아님)
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Controller
public class FileController {

    // [수정] @Autowired 제거 (단독 필드 주입 시 @Value만 있어도 됨)
    @Value("${spring.servlet.multipart.location}")
    private String uploadFolder;

    @PostMapping("/upload-email")
    public String uploadEmail(
            @RequestParam("email") String email,
            @RequestParam("subject") String subject,
            @RequestParam("message") String message,
            RedirectAttributes redirectAttributes) {

        try {
            // 1. 업로드 경로 가져오기 (java.nio.file.Path 사용)
            Path uploadPath = Paths.get(uploadFolder).toAbsolutePath();

            // 2. 폴더가 없으면 생성
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 3. 파일명 생성 (이메일에서 특수문자 제거 후 .txt 확장자 붙임)
            String sanitizedEmail = email.replaceAll("[^a-zA-Z0-9]", "_");
            Path filePath = uploadPath.resolve(sanitizedEmail + ".txt");

            System.out.println("File path: " + filePath); // 디버깅용 출력

            // 4. 파일 쓰기 (BufferedWriter 사용)
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
                writer.write("메일 제목: " + subject);
                writer.newLine(); // 줄 바꿈
                writer.write("요청 메시지:");
                writer.newLine();
                writer.write(message);
            }

            // 5. 성공 메시지 전달
            redirectAttributes.addFlashAttribute("message", "메일 내용이 성공적으로 업로드되었습니다!");
            return "upload_end"; // upload_end.html로 이동

        } catch (IOException e) {
            e.printStackTrace();
            // 6. 실패 메시지 전달 및 에러 페이지 이동
            redirectAttributes.addFlashAttribute("message", "업로드 중 오류가 발생했습니다.");
            return "/error_page/article_error";
        }

    }

}
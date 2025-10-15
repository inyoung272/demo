package com.model.domain;

import lombok.*;
import jakarta.persistence.*;

@Getter
@Entity
@Table(name = "article")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    // [수정됨] 세미콜론(;) 추가
    @Column(name = "title", nullable = false)
    private String title = "";

    @Column(name = "content", nullable = false)
    private String content = "";

    // [수정됨] 필드 선언 시 @Column 어노테이션이 @Builder 위에 있었습니다.
    // 이는 @Builder의 적용 범위에 영향을 주지 않으므로, Builder 어노테이션을 생성자 위에만 유지합니다.

    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) { // 현재 객체 상태 업데이트
        this.title = title;
        this.content = content;
    }
}
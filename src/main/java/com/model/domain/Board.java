package com.model.domain;

import lombok.*;
import jakarta.persistence.*;

@Getter
@Entity
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    // [수정됨] 세미콜론(;) 추가
    @Column(name = "title", nullable = false)
    private String title = "";

    @Column(name = "content", nullable = false)
    private String content = "";

    @Column(name = "user", nullable = false) // 이름
    private String user = "";
    @Column(name = "newdate", nullable = false) // 날짜
    private String newdate = "";
    @Column(name = "count", nullable = false) // 조회수
    private String count = "";
    @Column(name = "likec", nullable = false) // 좋아요
    private String likec = "";

    // [수정됨] 필드 선언 시 @Column 어노테이션이 @Builder 위에 있었습니다.
    // 이는 @Builder의 적용 범위에 영향을 주지 않으므로, Builder 어노테이션을 생성자 위에만 유지합니다.

    @Builder
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) { // 현재 객체 상태 업데이트
        this.title = title;
        this.content = content;
    }
}
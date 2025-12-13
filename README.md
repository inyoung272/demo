# C# 소스 코드 깃허브

새로운 시작! C#의 세계로 떠나보아요~

## Getting Started

This project is a starting point for a C# application.

A few resources to get you started if this is your first flutter project:

-[https://github.com/inyoung272/demo/tree/main]()

## DAY 1 환경 설정 및 깃허브 연동, Java 기초 살펴보기

JDK 설치 및 환경 변수 설정 완료

VS Code 개발 환경 구성

Spring Boot 프로젝트 생성 및 깃허브 연동 확인 완료!

## DAY 2 개발환경 설정 및 테스트

스프링 부트 웹 서버 구동 테스트 완료

ontroller 생성 및 테스트

메인 페이지 생성

[https://github.com/inyoung272/demo/blob/main/src/main/resources/templates/index.html]

## DAY 3 포트폴리오 작성하기

부트스트랩을 활용한 UI 디자인 적용

자기소개, 기술 스택, 프로젝트 경험 섹션 구성

정적 리소스 경로 설정 완료

[https://github.com/inyoung272/demo/blob/main/src/main/resources/templates/index.html]

## DAY 4 데이터베이스 연동 및 테스트

H2 Database 및 MySQL 연동 설정 (application.properties)

Member 엔티티 설계

Repository 인터페이스 생성

연습문제 완료(사용자 정보 DB 저장 및 출력 테스트)

## DAY 5 블로그 게시판(1) - 기본 CRUD

게시판 테이블(Article) 설계 및 엔티티 생성

글 목록 조회(findAll) 및 글 작성(save) 기능 구현

article_list.html, article_write.html 페이지

연습문제 완료(페이지 리다이렉트 처리)

## DAY 6 블로그 게시판(2) - 심화 기능

게시글 상세 보기, 수정(update), 삭제(delete) 기능 구현

@PutMapping, @DeleteMapping 활용

예외 처리 핸들러 추가 및 커스텀 에러 페이지(error_page) 연결

연습문제 완료(삭제 버튼 동작 및 예외 처리 페이지 확인)

## DAY 7 게시판 Entity 확장

기존 Article을 Board 엔티티로 확장

작성자(user), 작성일(newdate), 조회수(count), 좋아요(likec)
필드 추가

글 수정 보강: 제목/내용 수정 시 작성자, 조회수 등 기존 데이터 유지 처리 (update 메서드 수정)

## DAY 8 게시판 기능 추가 (검색 및 페이징)

페이징 기능 구현

PageRequest를 활용하여 한 페이지당 3개 글 출력 설정

제목 및 내용 기반 키워드 검색 기능 구현

페이징 네비게이션 바 UI 적용

## DAY 9 보안 기초

spring-boot-starter-validation 의존성 추가

회원가입 DTO에 유효성 검사 어노테이션

입력값 필터링 구현

@Valid, @Validated 적용하여 데이터 무결성 확보

## DAY 10 로그인 및 세션 관리

로그인 컨트롤러 구현

로그인 상태 유지 및 세션 ID 발급(HttpSession)

로그인 성공 시 세션에 사용자 이름 및 이메일 저장

로그아웃 기능 구현

로그인한 사용자만 본인 글 작성/수정/삭제 가능

## DAY 11 파일 업로드 및 최종 배포

메인 포트폴리오 페이지와 게시판, 회원가입 기능 최종 통합

\*모든 주차들 연습문제 완료

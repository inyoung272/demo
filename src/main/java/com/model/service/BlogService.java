package com.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.model.domain.Article;
import com.model.domain.Board;
import com.model.repository.BlogRepository;
import com.model.repository.BoardRepository;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 생성자자동생성(부분)
public class BlogService {

    private final BlogRepository blogRepository; // 리포지토리선언
    private final BoardRepository blogRepository2;

    // public List<Article> findAll() { // 게시판전체목록조회
    // return blogRepository.findAll();
    // }

    public List<Board> findAll() { // 게시판 전체 목록 조회
        return blogRepository2.findAll();
    }

    // public Article save(AddArticleRequest request) {
    // return blogRepository.save(request.toEntity());
    // }

    public Board save(AddArticleRequest request) {
        // DTO가 없는 경우 이곳에 직접 구현 가능
        return blogRepository2.save(request.toEntity());
    }

    // public Optional<Article> findById(Long id) { // 게시판 특정 글 조회
    // return blogRepository.findById(id);
    // }

    public Optional<Board> findById(Long id) { // 게시판 특정 글 조회
        return blogRepository2.findById(id);
    }

    @Transactional
    public Board update(Long id, AddArticleRequest request) {
        // 1. 게시글 찾기 (Article인지 Board인지 본인 클래스명에 맞게 수정하세요)
        Board article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다: " + id));

        // 2. [핵심 수정] 6개 정보를 모두 넣어줍니다.
        article.update(
                request.getTitle(), // 바꿀 제목
                request.getContent(), // 바꿀 내용
                article.getUser(), // [유지] 원래 작성자
                article.getNewdate(), // [유지] 원래 날짜
                article.getCount(), // [유지] 조회수
                article.getLikec() // [유지] 좋아요
        );

        return article;
    }

    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

    public Page<Board> findAll(Pageable pageable) {
        return blogRepository2.findAll(pageable);
    }

    public Page<Board> searchByKeyword(String keyword, Pageable pageable) {
        return blogRepository2.findByTitleContainingIgnoreCase(keyword, pageable);
    } // LIKE 검색 제공(대소문자 무시)

}
package com.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.model.domain.Article;
import com.model.repository.BlogRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 생성자자동생성(부분)
public class BlogService {

    private final BlogRepository blogRepository; // 리포지토리선언

    public List<Article> findAll() { // 게시판전체목록조회
        return blogRepository.findAll();
    }

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public Optional<Article> findById(Long id) { // 게시판 특정 글 조회
        return blogRepository.findById(id);
    }

    public void update(Long id, AddArticleRequest request) {
        Optional<Article> optionalArticle = blogRepository.findById(id); // 단일 글 조회
        optionalArticle.ifPresent(article -> { // 값이 있으면
            article.update(request.getTitle(), request.getContent()); // 값을 수정
            blogRepository.save(article); // Article 객체에 저장
        });
    }

    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

}
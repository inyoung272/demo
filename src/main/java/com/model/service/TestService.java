package com.model.service;

import java.util.List;
import com.model.domain.TestDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.domain.TestDB;
import com.model.repository.TestRepository;
import lombok.RequiredArgsConstructor;

@Service // 서비스 등록, 스프링 내부 자동 등록됨
@RequiredArgsConstructor // 생성자 생성 (Lombok이 final 필드를 주입함)

public class TestService {

    @Autowired
    // final 키워드를 추가하여 생성자 주입의 대상임을 명확히 하고, @Autowired는 제거합니다.
    private final TestRepository testRepository;

    // findByName 메서드 본문 구조를 올바르게 수정했습니다.
    public List<TestDB> findByName(String name) {
        return testRepository.findByName(name);
    }
}
package com.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.domain.TestDB;
import java.util.List; // 리스트 반환을 위해 import 추가

@Repository
public interface TestRepository extends JpaRepository<TestDB, Long> {

    // Find all TestDB entities by a name
    List<TestDB> findByName(String name);
    // 또는 List<TestDB> findAllByName(String name); 으로 선언하는 것이 더 명확합니다.
}
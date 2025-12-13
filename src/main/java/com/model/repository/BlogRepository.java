package com.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.domain.Board;

@Repository
public interface BlogRepository extends JpaRepository<Board, Long> {
}

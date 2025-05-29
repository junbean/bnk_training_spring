package com.example.jpaBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpaBoard.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>{

}

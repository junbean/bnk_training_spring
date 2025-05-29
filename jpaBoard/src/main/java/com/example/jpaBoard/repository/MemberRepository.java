package com.example.jpaBoard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpaBoard.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
	Optional<MemberEntity> findByUsernameAndPassword(String username, String password);
}

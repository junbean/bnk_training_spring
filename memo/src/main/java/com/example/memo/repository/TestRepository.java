package com.example.memo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.memo.entity.Test1;

@Repository
public interface TestRepository extends JpaRepository<Test1, Integer> {

}

package com.example.jpaEx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpaEx.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {

}

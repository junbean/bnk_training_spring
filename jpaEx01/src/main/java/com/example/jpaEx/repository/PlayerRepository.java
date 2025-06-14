package com.example.jpaEx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpaEx.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

}

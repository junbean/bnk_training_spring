package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	User findByUsername(String username);
}

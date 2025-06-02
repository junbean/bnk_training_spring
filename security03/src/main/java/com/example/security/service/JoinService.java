package com.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.security.entity.User;
import com.example.security.entity.UserDTO;
import com.example.security.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JoinService {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserRepository userRepository;

	public JoinService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User regist(UserDTO userDTO) {
		User entity = new User();
		entity.setUsername(userDTO.getUsername());
		String newPw = bCryptPasswordEncoder.encode(userDTO.getPassword());	// 비밀번호 암호화
		entity.setPassword(newPw);
		entity.setName(userDTO.getName());
		
		if(userDTO.getUsername().contains("admin")) {
			entity.setRole("ROLE_ADMIN");
		} else {
			entity.setRole("ROLE_MEMBER");	
		}
		
		log.info("회원가입 됨 : " + entity.toString());
		
		return userRepository.save(entity);
	}
	
}

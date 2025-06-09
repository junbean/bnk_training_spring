package com.example.securityTest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.securityTest.dto.UserDTO;
import com.example.securityTest.entity.UserEntity;
import com.example.securityTest.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	// 회원 가입
	public UserEntity registServie(UserDTO userDTO) {
		UserEntity userEntity = new UserEntity();
		
		// 비번 암호화
		String newPw = bCryptPasswordEncoder.encode(userDTO.getPassword());
		
		// userEntity에 값 넣기
		userEntity.setUsername(userDTO.getUsername());
		userEntity.setPassword(newPw);
		userEntity.setName(userDTO.getName());
		userEntity.setRole("ROLE_MEMBER");
		
		// 반환
		return userRepository.save(userEntity);
	}
	
}

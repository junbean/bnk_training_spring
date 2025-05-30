package com.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.security.dto.UserDTO;
import com.example.security.entity.User;
import com.example.security.repository.UserRepository;

@Service
public class JoinService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public User regist(UserDTO userDTO) {
		// 0. 서비스단에서 컨트롤러에서 전달한 파라미터를 받음
		// 1. 파라미터로 넘어온 passord를 암호화 처리
		String newPw = bCryptPasswordEncoder.encode(userDTO.getPassword());
		
		// 2. DTO를 Entity로 변환하는 작업 -> JPA에서 DB에 저장하기 위한 작업
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setPassword(newPw);
		user.setName(userDTO.getName());
		
		// 3. Role 정보 저장
		// username이 'admin'이라면 'ROLE_ADMIN'으로 지정
		// 그게 아니라면 'ROLE_MEMBER'로 지정
		if(userDTO.getUsername().contains("admin")) {
			user.setRole("ROLE_ADMIN");
		} else {
			user.setRole("ROLE_MEMBER");	
		}
		
		// 3. DB에 회원정보 저장
		return userRepository.save(user);
	}

}

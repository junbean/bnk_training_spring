package com.example.securityTest.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.securityTest.entity.UserEntity;
import com.example.securityTest.repository.UserRepository;

@Service
public class CustomUserDeatilsService implements UserDetailsService {
	private UserRepository userRepository;
	
	public CustomUserDeatilsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByUsername(username);
		
		System.out.println("무");
		
		if(userEntity != null) {
			System.out.println("야호");
			return new CustomUserDetails(userEntity);
		} else {
			System.out.println("....");
		}
		
		return null;
	}

}

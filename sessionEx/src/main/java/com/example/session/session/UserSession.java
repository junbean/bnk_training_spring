package com.example.session.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.session.dto.UserDto;

@Component
@SessionScope
public class UserSession {
	private UserDto loginUser;	// 로그인 사용자 정보 저장용 객체
	
	// 로그인 처리
	public void login(UserDto user) {
		// 로그인을 성공하면 세션객체에 로그인 정보를 저장
		loginUser = user;
	}
	
	// 로그아웃 처리
	public void logout() {
		loginUser = null;
	}
	
	// 현재 로그인 여부 확인
	public boolean isLogedIn() {
		// true : 로그인된 상태		
		// false : 로그인 안된 상태
		return loginUser != null;	
	}
	
	// 사용자 정보 조회
	public UserDto getLoginUser() {
		return loginUser;
	}
	
}

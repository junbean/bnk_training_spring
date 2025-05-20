package com.example.board2.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.board2.dto.MemberDTO;

@Component
@SessionScope
public class MemberSession {
	private MemberDTO member;
	
	// 로그인 처리
	public void login(MemberDTO member) {
		this.member = member;
	}
	
	// 로그 아웃
	public void logout() {
		this.member = null;
	}
	
	// 현재 로그인 여부 확인
	public boolean isLogedIn() {
		return this.member != null;
	}
	
	// 사용자 정보 조회
	public MemberDTO getMember() {
		return this.member;
	}
}

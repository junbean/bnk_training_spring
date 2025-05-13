package com.example.theater.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.theater.dao.IMemberDao;
import com.example.theater.dto.MemberDTO;

import jakarta.servlet.http.HttpSession;
import oracle.jdbc.proxy.annotation.Post;

@RestController
@RequestMapping("/member")
public class MemberController {
	private IMemberDao memberDao;
	
	@Autowired
	public MemberController(IMemberDao dao) {
		memberDao = dao;
	}
	
	// 로그인
	@PostMapping("/login")
	public Map<String, Object> login(
		@RequestParam("userId") String userId,
		@RequestParam("password") String password,
		HttpSession session	
	) {
		// Map 객체 생성
		Map<String, Object> result = new HashMap<>();

		// 로그인 실행
		MemberDTO member = memberDao.getMemberbyLogin(userId, password);
		
		// 멤버 객체 조건문
		if(member != null) {
			session.setAttribute("loginUser", member);	// 세션에 로그인 사용자 저장
			result.put("success", true);
			result.put("userId", member.getUserId()); // 필요한 추가 정보
		} else {
			result.put("success", false);
		}
		
		// 결과 반환
		return result;
	}
	
	
	// 로그아웃 
	@PostMapping("/logout")
	public Map<String, Object> logout(HttpSession session) {
	    session.invalidate(); // 세션 무효화 (로그아웃 처리)

	    Map<String, Object> result = new HashMap<>();
	    result.put("success", true);
	    return result;
	}

	
	
	// 아이디 중복 확인
	@GetMapping("/checkId")
	public Map<String, Object> checkUserId(
		@RequestParam("userId") String userId
	) {
		Map<String, Object> map = new HashMap<>();
		boolean exists = memberDao.checkUserIdExists(userId) > 0;
		map.put("exists", exists);
		return map;
	}
	
	
	// 회원가입
	@PostMapping("/regist")
	public Map<String, Object> regist(
		@RequestBody MemberDTO member
	) {
		Map<String, Object> result = new HashMap<>();
		int success = memberDao.insertMember(member);
		result.put("success", success > 0);
		return result;
	}
	
}

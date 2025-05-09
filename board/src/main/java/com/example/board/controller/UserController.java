package com.example.board.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.board.dao.IUserDao;
import com.example.board.dto.UserDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private IUserDao userDao;
	
	@Autowired
	public UserController(IUserDao dao) {
		userDao = dao;
	}
	
	
	@GetMapping("/loginForm") 
	public String userLoginForm() {
		System.out.println("로그인 페이지");
		return "user/userLogin";
	}
	
	@GetMapping("/registForm") 
	public String userRegistForm() {
		System.out.println("회원 가입 페이지");
		return "user/userRegist";
	}
	
	@PostMapping("/login")
	public String login(
		@RequestParam("userId") String userId,
		@RequestParam("password") String password,
		HttpServletRequest request
	) {
		System.out.println("로그인 수행");
		UserDTO user = userDao.selectToLogin(userId, password);
		
		if(user != null) {
			// 로그인 성공
	        System.out.println("로그인 성공");
	        HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
			// session.setMaxInactiveInterval(1800);	// 세션 타임아웃 설정 - 30분
	        return "redirect:/post/list";
		} else {
			// 로그인 실패
	        System.out.println("로그인 실패");
	        return "redirect:/user/loginForm";
		}
	}
	
	@PostMapping("/regist")
	public String regist(
		@RequestParam("userId") String userId,
		@RequestParam("password") String password,
		@RequestParam("name") String name,
		@RequestParam("email") String email,
		@RequestParam("phone") String phone
	) {
		System.out.println("회원가입 수행");
		
		UserDTO user = new UserDTO();
		user.setUserId(userId);
		user.setPassword(password);
		user.setName(name);
		user.setEmail(email);
		user.setPhone(phone);
		user.setRole('U');
		
		int result = userDao.insert(user);
		
		if(result > 0) {
			// 회원가입 성공
	        System.out.println("회원가입 성공");
			return "redirect:/user/loginForm";
		} else {
			// 회원가입 실패
	        System.out.println("회원가입 실패");
	        return "redirect:/user/registForm";
		}
	}
	
	
}

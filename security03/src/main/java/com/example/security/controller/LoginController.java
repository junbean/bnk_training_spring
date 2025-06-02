package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String loginForm(
		@RequestParam(name="error", required=false) String error,
		@RequestParam(name="logout", required=false) String logout,
		@RequestParam(name="needLogin", required=false) String needLogin,
		Model model
	) {
		if(error != null) {
			model.addAttribute("msg", "로그인중 에러가 발생했습니다...");
		}
		
		if(logout != null) {
			model.addAttribute("msg", "로그아웃 되었어요...");
		}
		
		if(needLogin != null) {
			model.addAttribute("msg", "회원 전용 페이지입니다...");
		}
		
		return "loginForm";
	}

	@GetMapping("/success")
	public String success() {
		return "success";
	}
}

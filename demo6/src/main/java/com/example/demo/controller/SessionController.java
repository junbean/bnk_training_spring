package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class SessionController {
	
	@PostMapping("/extend-session")
	public void extendSession(HttpSession session) {
		session.setMaxInactiveInterval(60);	// 60초 연장
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(
		@RequestParam("id") String id,
		@RequestParam("password") String password,
		HttpSession session,
		RedirectAttributes redirectAttributes
	) {
		if(id.equals("aaa") && password.equals("111")) {
			session.setAttribute("loginUser", id);
			return "redirect:/sessionTest";
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "아이디 또는 패스워드가 일치하지 않습니다.");
			return "redirect:/login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/login";
	}
	
}

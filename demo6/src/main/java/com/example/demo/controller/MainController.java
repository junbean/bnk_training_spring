package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String root() {
		return "index";
	}
	
	// 회원 전용 페이지
	@GetMapping("/sessionTest")
	public String sessionTest(HttpSession session, RedirectAttributes rttr) {
		if(session.getAttribute("loginUser") == null) {
			rttr.addFlashAttribute("alertMessage", "회원전용 페이지입니다. 로그인 페이지로 이동합니다");
			return "redirect:/login";
		} else {
			return "sessionTest";
		}
	}
	
	/*
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
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
			return "redirect:/loginForm";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginUser");
		return "redirect:/";
	}
	*/
	
}

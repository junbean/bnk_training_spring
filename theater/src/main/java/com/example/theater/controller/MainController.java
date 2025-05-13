package com.example.theater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.theater.dto.MemberDTO;

import jakarta.servlet.http.HttpSession;


@Controller
public class MainController {
	@GetMapping("/")
	public String root(HttpSession session, Model model) {
		MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
		model.addAttribute("loginUser", loginUser);		
		return "index";
	}
	
	@GetMapping("login")
	public String loginForm() {
		return "member/memberLogin";
	}
	
	@GetMapping("regist")
	public String registForm() {
		return "member/memberRegist";
	}
	
}

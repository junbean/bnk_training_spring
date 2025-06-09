package com.example.securityTest.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.securityTest.auth.CustomUserDetails;
import com.example.securityTest.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
	@GetMapping("/success")
	public String loginSuccess(
		Model model, 
		Principal principal ,
		@AuthenticationPrincipal CustomUserDetails customUserDetails
	) {
		log.info("member/welcome/....");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		// SeurityContextHolder를 통해 Authentication 객체 획득 후 CustomUserDetails 객체 얻어내기
		// 정보 얻기
		model.addAttribute("username", userDetails.getUsername());
		model.addAttribute("name", userDetails.getName());
		model.addAttribute("role", userDetails.getRole());
		
		// Principal 객체 주입 
		model.addAttribute("username2", principal.getName());
		model.addAttribute("principal", principal.toString());
		
		// @AuthenticationPrincipal 사용
		model.addAttribute("username3", customUserDetails.getUsername());
		model.addAttribute("name3", customUserDetails.getName());
		model.addAttribute("role3", customUserDetails.getRole());
		return "/user/success";
	}
	
	
	@GetMapping("/mypage")
	public String mypage() {
		return "/user/mypage";
	}

}

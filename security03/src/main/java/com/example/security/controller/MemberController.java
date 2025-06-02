package com.example.security.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.security.auth.CustomUserDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/members")
public class MemberController {
	
	@PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
	@GetMapping("/mypage")
	public String mypage(
		@AuthenticationPrincipal CustomUserDetails customUserDetails,
		Principal principal,
		Model model
	) {
		log.info("members/welcome/....");
		
		model.addAttribute("user", customUserDetails);
		
		return "/members/mypage";
	}
	
}

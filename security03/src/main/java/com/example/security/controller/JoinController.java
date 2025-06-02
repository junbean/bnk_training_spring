package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.security.entity.User;
import com.example.security.entity.UserDTO;
import com.example.security.service.JoinService;

@Controller
public class JoinController {

	private JoinService joinService;
	
	public JoinController(JoinService service) {
		joinService = service;
	}
	
	@GetMapping("/regist")
	public String registForm() {
		return "registForm";
	}
	
	@PostMapping("/registProc")
	public String regist(UserDTO userDTO) {
		User result = joinService.regist(userDTO);
		if(result != null) {
			return "redirect:/login";
		} 

		return "redirect:/regist";
	}
}

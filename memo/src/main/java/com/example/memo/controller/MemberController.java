package com.example.memo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.memo.entity.MemberEntity;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@GetMapping("/registForm")
	public String registForm(Model model) {
		model.addAttribute("member", new MemberEntity());
		return "/membersForm";
	}
	
	@PostMapping("/regist")
	public String regist(
		@Valid @ModelAttribute MemberEntity memberEntity, 
		BindingResult result
	) {
		if(result.hasErrors()) {
			return "/membersForm";
		}
		
		return "redirect:/members";
	}
}

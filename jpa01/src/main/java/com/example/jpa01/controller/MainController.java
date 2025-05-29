package com.example.jpa01.controller;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.jpa01.entity.MemberEntity;
import com.example.jpa01.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
	@Autowired
	MemberRepository memberRepository;
	
	/*
	@GetMapping("/")
	public @ResponseBody String root() {
		return "Hello JPA--";
	}
	*/
	
	@GetMapping("/")
	public String root() {
		log.info("root----------------------");
		// System.out.println("root----------------------");
		return "index";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		log.info("list----------------------");
		List<MemberEntity> list = memberRepository.findAll();
		
		model.addAttribute("list", list);
		
		return "list";
	}

	@GetMapping("/regist")
	public String registForm() {
		log.info("registForm----------------------");
		return "regist";
	}
	
	@PostMapping("/regist")
	public String regist(MemberEntity memberEntity, RedirectAttributes rttr) {
		log.info("regist----------------------");
		
		memberEntity.setRole("MEMBER");
		MemberEntity m = memberRepository.save(memberEntity);
		
		rttr.addFlashAttribute("member", m);
		
		return "redirect:/success";
	}
	
	@GetMapping("/success")
	public String success() {
		log.info("success----------------------");
		return "success";
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam("username") String username, Model model) {
		log.info("detail----------------------");

		// Optional<MemberEntity> result = memberRepository.findById(username);
		// MemberEntity m = result.get();
		
		MemberEntity result = memberRepository.findByUsername(username);
		
		model.addAttribute("member", result);
		
		return "detail";
	}
	
	@GetMapping("/update")
	public String updateForm() {
		return "update";
	}
	
	@PostMapping("/update")
	public String update(MemberEntity memberEntity, RedirectAttributes rttr) {
		memberEntity.setRole("MEMBER");
		MemberEntity m = memberRepository.save(memberEntity);
		rttr.addAttribute("username", m.getUsername());
		return "redirect:/detail";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("username") String username) {
		memberRepository.deleteById(username);
		
		return "redirect:/list";
	}
	
	
	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
		Long count = memberRepository.countByUsernameAndPassword(username, password);
		
		if(count > 0) {
			System.out.println("로그인 식별 됨");	
			return "redirect:/";
		} else {
			System.out.println("로그인 식별 안됨");
			return "redirect:/login";
		}
	}
	
	
}

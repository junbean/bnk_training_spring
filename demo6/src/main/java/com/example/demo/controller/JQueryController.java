package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.MemberDTO;

@Controller
public class JQueryController {
	@GetMapping("/jQuery")
	public String jQuery() {
		return "jQuery";
	}
	
	@GetMapping("/api/data")
	public @ResponseBody String getData() {
		System.out.println("get 요청 받음");
		return "<h3>GET - api데이터</h3>";
	}

	@PostMapping("/api/data")
	public @ResponseBody String postData(@RequestBody MemberDTO memberDTO) {
		System.out.println("post 요청 받음");
		System.out.println(memberDTO);
		return "<h3>POST - api데이터</h3><h3>" + memberDTO.getName() + ", " + memberDTO.getAge() + "</h3>";
	}
	
	@GetMapping("/api/param")
	public @ResponseBody String getParam(@RequestParam("name") String name, @RequestParam("age") int age) {
		System.out.println("get 요청 받음");
		MemberDTO member = new MemberDTO();
		member.setName(name);
		member.setAge(age);
		return "<h3>GET - api데이터</h3>"
				+ "<h3>" + member + "</h3>";
	}
}

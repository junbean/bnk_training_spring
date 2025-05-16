package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "*")
public class CookController {
	
	@GetMapping("/cook/maindish")
	public @ResponseBody String maindish() {
		System.out.println("불려나감");
		return "메인 요리가 나옵니다!";
	}
}

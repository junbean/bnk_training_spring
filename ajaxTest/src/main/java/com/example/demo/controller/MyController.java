package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*

return 활용법
	1. view를 지정한다
	2. 데이터를 전송한다
		a. @ResponseBody를 사용한다
		b. @RestController를 사용한다
*/
@Controller 
public class MyController {
	
	@RequestMapping("/")
	public String root() {
		System.out.println("root 경로");
		return "index";
	}
	
	
}

package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class MyRestController {
	
	@GetMapping("/req1")
	public String req1() {
		System.out.println("req1 경로");
		return "Hello Ajax...";
	}
	
	@GetMapping("/req2_1")
	public String req2_1() {
		System.out.println("req2_1 경로");
		// 이름 : David
		// 나이 : 28
		
		// \" = 문자열 안에 " 기호 표시
		return "{\"name\" : \"David\", \"age\" : 28}";
	}
	

	@GetMapping("/req2_2")
	public String req2_2() throws JsonProcessingException {
		System.out.println("req2_2 경로");
		
		// dto 객체 사용
		User user = new User();
		user.setName("James Dean");
		user.setAge(24);
		
		// Jackson 라이브러리를 활용한 JSON 데이터 만들기
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = mapper.writeValueAsString(user);
		System.out.println(jsonStr);
		
		return jsonStr;
	}
	
	@GetMapping("/req3")
	public String req3(@RequestParam("param") String param) {
		System.out.println("req3 경로 - " + param);
		return "Good!";
	}
	
	@GetMapping("/req4/{name}/{age}")
	public String req4(
		@PathVariable("name") String name,
		@PathVariable("age") int age
	) {
		System.out.println("req4 경로 - " + name + ", " + age);
		return "Ok Good!!";
	}
	
	@GetMapping("/req5")
	public String req5(User user) {
		System.out.println("req5 경로 - " + user);
		return "Well Done!!!";
	}
	
	//===============================================================================================
	// POST 요청 작업
	
	@PostMapping("/req6")
	public String req6(
		@RequestParam("name") String name,
		@RequestParam("age") int age 
	) {
		System.out.println("req6 경로 - " + name + ", " + age);
		return "BE CAREFULL!";
	}
	
	@PostMapping("/req7")
	public String req7(User user) {
		System.out.println("req7 경로 - " + user);
		return "NICE!!";
	}
	
	@PostMapping("/req8")
	public String req8(@RequestBody User user) {
		System.out.println("req8 경로 - " + user);
		return "BEAUTIFUL!!!";
	}
}

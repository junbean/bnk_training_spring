package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.MemberDAO;

@Controller
public class MyController {	

	@Autowired
    private MemberDAO memberDAO;
	
	@GetMapping("/")  // @RequestMapping("/") 대신 사용
	public String root() {
		System.out.println("index 경로");
		return "index";
	}
}

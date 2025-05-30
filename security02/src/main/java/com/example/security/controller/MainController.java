package com.example.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.security.dto.UserDTO;
import com.example.security.entity.User;
import com.example.security.service.JoinService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
	
	@Autowired
	private JoinService joinService;
	
	@GetMapping("/")
	public String root() {
		return "index";
	}
	
	@GetMapping("/regMember")
	public String regMember() {
		return "registForm";
	}
	
	@PostMapping("/regist")
	public @ResponseBody String regist(UserDTO userDTO) {
		log.info("regist==========");

		// 파라미터 서비스에 전달 -> 서비스에서 회원가입 처리를 할 것임
		User result = joinService.regist(userDTO);
		
		if(result != null) {
			return "SUCESS REGIST";
		} else {
			return "FAIL REGIST";
		}
	}
	
   @GetMapping("/login")
   public String loginForm() {
      return "loginForm";
   }
   
   @GetMapping("/fail")
   public String fail() {
      return "fail"; 
   }

   /*
   @PostMapping("/loginProc")
   public String loginProc() {
      
      return ""; 
   }
   */

}

/*

*/
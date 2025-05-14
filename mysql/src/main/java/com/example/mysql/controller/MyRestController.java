package com.example.mysql.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mysql.dto.UserDTO;

@RestController
@RequestMapping("/api")
public class MyRestController {
	
	@GetMapping("/")
	public String root() {
		return "API test...";
	}
	
	// run()에 대응되는 url
	@GetMapping("/user/{idx}")
	public UserDTO getUserPath(
		@PathVariable("idx") int idx
	) {
		System.out.println("idx : " + idx);
		List<UserDTO> userList = new ArrayList<>();
		userList.add(new UserDTO("user1", "사용자A"));
		userList.add(new UserDTO("user2", "사용자B"));
		userList.add(new UserDTO("user3", "사용자C"));
		
		return userList.get(idx);
	}

	// run1()에 대응되는 url
	@GetMapping("/user")
	public UserDTO getUserParam(
		@RequestParam("id") String id,
		@RequestParam("name") String name
	) {
		System.out.println(id + ", " + name);
		return new UserDTO(id, name);
	}

	// run2()에 대응되는 url
	@PostMapping("/userJson")
	public UserDTO postUserJson(
		@RequestBody UserDTO user
	) {
		System.out.println(user);
		return user;
	}

	// run3()에 대응되는 url
	@PostMapping("/userForm")
	public UserDTO postUserForm(
		@RequestParam("id") String id,
		@RequestParam("name") String name 
	) {
		System.out.println(id + ", " + name);
		return new UserDTO(id, name);
	}

	/*
	// run3() 방식으로는 이것도 가능함 
	@PostMapping("/user")
	public UserDTO postUserForm(
		UserDTO user
	) {
		System.out.println(user);
		return user;
	}
	*/
	
}

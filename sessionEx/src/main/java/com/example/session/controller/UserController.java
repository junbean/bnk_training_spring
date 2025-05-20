package com.example.session.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.session.dao.IUserDao;
import com.example.session.dto.UserDto;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")		// http://localhost:8090/user 로 매핑된다
public class UserController {
	@Autowired
	private IUserDao userDao;
	
	@GetMapping("/userlist")
	public String userlist(Model model) {
		List<UserDto> list = userDao.getUserList();
		model.addAttribute("list", list);
		return "/user/userlist";
	}
	

	@GetMapping("/search")
	public String usersearch(Model model) {
		System.out.println("usersearch");
		return "/user/search";
	}
	
	@GetMapping("/result")
	public String userSearch(UserDto userDto, Model model) {
		System.out.println("userSearch");
		List<UserDto> searchList = userDao.getUserListWithParam(userDto);
		model.addAttribute("searchList", searchList);
		return "/user/result";
	}

	
	@GetMapping("/result2")
	public String userSearch2(UserDto userDto, Model model) {
		System.out.println("userSearch2");
		List<UserDto> searchList = userDao.getUserListWithParam2(userDto);
		model.addAttribute("searchList", searchList);
		return "/user/result";
	}
	
	/*
	@GetMapping("/result")
	public String userSearch(UserDto userDto, Model model) {
		System.out.println("특정 user list 연산" + userDto);
		List<UserDto> searchList = userDao.getUserListWithParam(userDto);
		System.out.println(searchList);
		model.addAttribute("searchList", searchList);
		return "redirect:/user/search";
	}
	*/
	
	// ================================================================
	// ================================================================
	
	@GetMapping("/create")
	public String insertForm(Model model) {
	    model.addAttribute("userDto", new UserDto());
		return "/user/regForm";
	}
	
	@PostMapping("/create")
	public String insert(@Valid UserDto userDto, BindingResult result) {
		System.out.println(userDto);
		System.out.println(result);
		
		if (result.hasErrors()) {
			if (result.getFieldError("id") != null) {
				System.out.println("1: " + result.getFieldError("id").getDefaultMessage());
		        return "/user/regForm"; // 폼으로 다시 돌아감
			}
			if(result.getFieldError("pw") != null) {
				System.out.println("2: " + result.getFieldError("pw").getDefaultMessage());
		        return "/user/regForm"; // 폼으로 다시 돌아감
			}
		}
	    return "redirect:/user/success";
	}
	
	@GetMapping("/success")
	public String success() {
	    return "/user/success";
	}
}

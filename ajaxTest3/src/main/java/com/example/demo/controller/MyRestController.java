package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.MemberDAO;
import com.example.demo.dto.MemberDTO;

@RestController
public class MyRestController {
	
	@Autowired
    private MemberDAO memberDAO;
	
	@GetMapping("/duplicateId")
	public Boolean duplicateId(
		@RequestParam("id") String id) 
	{
	    System.out.println(id);
	    MemberDTO member = memberDAO.viewMember(id);
	    return member != null;
	}
	
	
	
}

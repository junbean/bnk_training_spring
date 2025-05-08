package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.MemberDAO;
import com.example.demo.dto.MemberDTO;

import jakarta.servlet.http.HttpSession;

@RestController
public class MyRestController {
	
	@Autowired
    private MemberDAO memberDAO;
	
	@GetMapping("/duplicateId")
	public Boolean duplicateId(
		@RequestParam("id") String id) 
	{
	    System.out.println("아이디 중복 확인---");
	    MemberDTO member = memberDAO.viewMember(id);
	    return member != null;
	}
	
	@PostMapping("/regist")
	public int registMember(
		@RequestParam("id") String id,
		@RequestParam("pw") String pw,
		@RequestParam("name") String name,
		@RequestParam("phone") String phone
	) {
		System.out.println("회원 등록---" + id + ", " + pw + ", " + name + ", " + phone);
		
		// 객체 생성
		MemberDTO member = new MemberDTO();
		member.setId(id);
		member.setPw(pw);
		member.setName(name);
		member.setPhone(phone);
		member.setGrade("C");
		
		int result = memberDAO.writeMember(member);
		
		return result;
	}
	
	@GetMapping("/memberList")
	public List<MemberDTO> getMemberList() {
		System.out.println("회원 목록---");
		List<MemberDTO> list = memberDAO.list();
		return list;
	}
	
	@PostMapping("/delete")
	public int deleteMember(
		@RequestParam("id") String id
	) {
		System.out.println("회원 삭제---");
		int result = memberDAO.deleteMember(id);
		return result;
	}
	
}

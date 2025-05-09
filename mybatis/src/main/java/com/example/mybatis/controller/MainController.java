package com.example.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mybatis.dao.IMemberDao;
import com.example.mybatis.dto.MemberDTO;


@Controller
public class MainController {
	
	private IMemberDao memberDao;
	
	@Autowired
	public MainController(IMemberDao dao) {
		memberDao = dao;
	}
	
	@GetMapping("/")
	public String root() {
		System.out.println("root===");
		return "index";
	}
	
	@GetMapping("/list")
	public String list(Model model) {		
		List<MemberDTO> list = memberDao.getList();
		model.addAttribute("list", list);
		return "memberList";
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam("id") String id, Model model) {
		MemberDTO member = memberDao.getMember(id);
		model.addAttribute("member", member);
		return "detail";
	}
	
	@PostMapping("/modify")
	public String modifiy(MemberDTO member) {
		System.out.println("modify param : " + member);
		memberDao.update(member);
		return "redirect:/list";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") String id) {
		System.out.println("삭제");
		memberDao.delete(id);
		return "redirect:/list";
	}
	
	@GetMapping("/insertForm")
	public String insertForm() {
		System.out.println("등록 폼");
		return "insertForm";
	}
	
	@PostMapping("/insert")
	public String insert(
		@RequestParam("id") String id,
		@RequestParam("pw") String pw,
		@RequestParam("name") String name,
		@RequestParam("phone") String phone
	) {
		System.out.println("등록");
		
		MemberDTO member = new MemberDTO();
		member.setId(id);
		member.setPw(pw);
		member.setName(name);
		member.setPhone(phone);
		member.setGrade("C");
		
		memberDao.insert(member);
		return "redirect:/list";
	}
}	

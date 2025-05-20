package com.example.board2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.board2.dto.MemberDTO;
import com.example.board2.service.MemberService;
import com.example.board2.session.MemberSession;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberSession memberSession;
	@Autowired
	private MemberService memberService;
	
	@GetMapping("login")
	public String loginForm() {	
		return "login";
	}
	
	@PostMapping("login")
	public String login(MemberDTO memberDTO, Model model, RedirectAttributes rttr) {
		MemberDTO result = memberService.checkMember();
		
		if(result != null) {
			// 회원 식별됨
			memberSession.login(result);
			return "/board/list";
		} else {
			// 회원 식별 안됨
			rttr.addFlashAttribute("msg", "아이디 또는 패스워드가 일치하지 않습니다");
			return "redirect:/member/login";
		}
	}
	
	@PostMapping("regist")
	public String regist(MemberDTO memberDTO, Model model, RedirectAttributes rttr) {
		MemberDTO result = memberService.checkMember();
		
		if(result != null) {
			// 회원 식별됨
			memberSession.login(result);
			return "/board/list";
		} else {
			// 회원 식별 안됨
			rttr.addFlashAttribute("msg", "아이디 또는 패스워드가 일치하지 않습니다");
			return "redirect:/member/login";
		}
	}
}

/*
 *
그냥 게시판만 mapper로 구현하세요

테이블은 tbl_member, tbl_board
걍 user01을 사용하셈

*/

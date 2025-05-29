package com.example.jpaBoard.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jpaBoard.entity.BoardEntity;
import com.example.jpaBoard.entity.MemberEntity;
import com.example.jpaBoard.repository.BoardRepository;
import com.example.jpaBoard.repository.MemberRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private BoardRepository boardRepository;
	
	
	@GetMapping("/")
	public String root() {
		return "index";
	}

	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(
		@RequestParam("username") String username, 
		@RequestParam("password") String password, 
		HttpSession session
	) {
		Optional<MemberEntity> result =  memberRepository.findByUsernameAndPassword(username, password);
		MemberEntity member = result.get();
		
		if(member == null) {
			return "redirect:/login";
		} else {
			session.setAttribute("loginUser", member);
			return "redirect:/list";
		}
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/regist")
	public String registForm() {
		return "regist";
	}
	
	@PostMapping("/regist")
	public String regist(MemberEntity memberEntity) {
		System.out.println("sadf");
		MemberEntity member =  memberRepository.save(memberEntity);
		
		if(member == null) {
			return "redirect:/regist";
		} else {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/list")
	public String list(HttpSession session, Model model) {
		MemberEntity loginUser = (MemberEntity) session.getAttribute("loginUser");
		if(loginUser == null) {
			return "redirect:/login";
		} else {
			List<BoardEntity> list = boardRepository.findAll();
			model.addAttribute("boardList", list);
			return "list";
		}
	}
	
	@GetMapping("/write")
	public String writeForm(HttpSession session, Model model) {
		MemberEntity loginUser = (MemberEntity) session.getAttribute("loginUser");
		if(loginUser == null) {
			return "redirect:/login";
		} else {
			model.addAttribute("loginUser", loginUser);
			return "write";
		}
	}
	
	@PostMapping("/write")
	public String write(BoardEntity boardEntity) {
		BoardEntity board = boardRepository.save(boardEntity);
		if(board == null) {
			return "redirect:/write";
		} else {
			return "redirect:/list";
		}
		
	}
	
	
	@GetMapping("/detail")
	public String list(@RequestParam("id") Long id, Model model) {
		Optional<BoardEntity> board = boardRepository.findById(id);
		BoardEntity boardInfo = board.get();
		
		model.addAttribute("boardInfo", boardInfo);
		
		return "detail";
	}
	
	
}

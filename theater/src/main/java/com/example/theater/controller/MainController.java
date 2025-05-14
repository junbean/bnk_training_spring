package com.example.theater.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.theater.dao.IPerformanceDao;
import com.example.theater.dao.IQnaDao;
import com.example.theater.dto.MemberDTO;
import com.example.theater.dto.QnaDTO;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

    private final QnaController qnaController;
	private final IPerformanceDao performanceDao;
	private final IQnaDao qnaDao;
	
	@Autowired
	public MainController(IPerformanceDao pDao, IQnaDao qDao, QnaController qnaController) {
		performanceDao = pDao;
		qnaDao = qDao;
		this.qnaController = qnaController;
	}
	
	@GetMapping("/")
	public String root(HttpSession session, Model model) {
		// 로그인 유저 정보 세션에서 가져오기
		MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
		// 사용자 정보 model에 저장
		model.addAttribute("loginUser", loginUser);		
		// 상영예정 공연 가져오기 
        model.addAttribute("upcomingList", performanceDao.getPerformanceUpComingList());
        // 상영 중인 공연 가져오기
        model.addAttribute("ongoingList", performanceDao.getPerformanceOnGoingList());
        // 상영 종료 공연 가져오기
        model.addAttribute("closedList", performanceDao.getPerformanceClosedList());

		return "index";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "member/memberLogin";
	}
	
	@GetMapping("/logout")
	public String logoutForm(HttpSession session) {
	    session.removeAttribute("loginUser");
	    return "redirect:/";
	}
	
	@GetMapping("/regist")
	public String registForm() {
		return "member/memberRegist";
	}
	
	@GetMapping("/qna")
	public String qnaList(Model model) {
		List<QnaDTO> qnaList = qnaDao.getAllQnaList();
		model.addAttribute("qnaList", qnaList);
		return "qna/qnaList";
	}
	
	@GetMapping("/qna/detail")
	public String qnaDetail(@RequestParam("id") Long id, Model model) {
		QnaDTO qnaItem = qnaDao.getQnaById(id);
		model.addAttribute("qnaItem", qnaItem);
		return "qna/qnaDetail";
	}
	
	@GetMapping("/qna/qnaForm")
	public String qnaForm() {
		return "qna/qnaForm";
	}
}

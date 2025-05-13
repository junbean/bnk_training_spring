package com.example.theater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.theater.dao.IPerformanceDao;
import com.example.theater.dto.MemberDTO;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	private IPerformanceDao performanceDao;
	
	@Autowired
	public MainController(IPerformanceDao dao) {
		performanceDao = dao;
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
        
        System.out.println(performanceDao.getPerformanceClosedList().get(0));

		return "index";
	}
	
	@GetMapping("login")
	public String loginForm() {
		return "member/memberLogin";
	}
	
	@GetMapping("regist")
	public String registForm() {
		return "member/memberRegist";
	}
	
}

package com.example.theater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.theater.dao.IPerformanceDao;
import com.example.theater.dto.PerformanceDTO;

@Controller
@RequestMapping("/performance")
public class PerformanceViewController {
	private final IPerformanceDao performanceDao;

	@Autowired
	public PerformanceViewController(IPerformanceDao dao) {
		this.performanceDao = dao;
	}
	
	@GetMapping("/detail")
	public String showPerformanceDetail(
		@RequestParam("id") int performanceId, 
		Model model
	) {
		PerformanceDTO performance = performanceDao.getPerformanceById(performanceId);
		model.addAttribute("performance", performance);
		return "performance/performanceDetail"; 
	}
}

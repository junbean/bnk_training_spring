package com.example.theater.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.theater.dao.IPerformanceDao;
import com.example.theater.dto.MemberDTO;
import com.example.theater.dto.PerformanceDTO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/performance")
public class PerformanceController {
	private IPerformanceDao performanceDao;
	
	@Autowired
	public PerformanceController(IPerformanceDao dao) {
		performanceDao = dao;
	}

	// 전체 공연 조회
	@GetMapping("/list")
	public List<PerformanceDTO> getAllPerformances() {
		return performanceDao.getPerformanceList();
	}
	
	// 상영 예정 공연 조회
	@GetMapping("/upcoming")
	public List<PerformanceDTO> getUpComingPerformances() {
		return performanceDao.getPerformanceUpComingList();
	}
	
	// 상영 중인 공연 조회
	@GetMapping("/ongoing")
	public List<PerformanceDTO> getOnGoingPerformances() {
		return performanceDao.getPerformanceOnGoingList();
	}
	
	// 상영 종료 공연 조회
	@GetMapping("/closed")
	public List<PerformanceDTO> getClosedPerformances() {
		return performanceDao.getPerformanceClosedList();	
	}
	
	/*
	개선점 
	
	파라미터 -> 조건을 통한 조회	
	/performance → 전체
	/performance?status=ongoing
	/performance?status=upcoming
	
	@GetMapping
	public List<PerformanceDTO> getPerformances(@RequestParam(required = false) String status) {
	    switch (status) {
	        case "ongoing": return performanceDao.getPerformanceOnGoingList();
	        case "upcoming": return performanceDao.getPerformanceUpComingList();
	        case "closed": return performanceDao.getPerformanceClosedList();
	        default: return performanceDao.getPerformanceList(); // 전체
	    }
	} 
	
	
	예외 처리 또는 응답 래핑 미흡
	현재는 List<PerformanceDTO>를 바로 반환하는데,
	실제로는 응답 성공/실패, 총 개수 등을 감싸는 래핑 구조가 실무에서는 권장됩니다.
	
	@GetMapping("/ongoing")
	public Map<String, Object> getOngoingPerformances() {
	    Map<String, Object> result = new HashMap<>();
	    List<PerformanceDTO> list = performanceDao.getPerformanceOnGoingList();
	
	    result.put("success", true);
	    result.put("count", list.size());
	    result.put("data", list);
	
	    return result;
	}
	
	*/
	
	
	// 공연 객체 가져오기 - performanceId 특정
	@GetMapping("/{id}")
	public PerformanceDTO getPerformanceById(
		@PathVariable("id") int performanceId
	) {
		return performanceDao.getPerformanceById(performanceId);
	}
	
	
	// 공연 데이터 삽입 - 세션의 loginUser에서 isAdmin이 true일 경우에만
	@PostMapping("/insert")
	public Map<String, Object> insertPerformance(
		@RequestBody PerformanceDTO performance,
		HttpSession session
	) {
		// Map 객체 생성
		Map<String, Object> result = new HashMap<>();

		MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");

		if (loginUser == null) {
			result.put("success", false);
			result.put("message", "로그인이 필요합니다.");
			return result;
		}
		
		// isAdmin 확인
		if (!Boolean.TRUE.equals(loginUser.isAdmin())) {
			result.put("success", false);
			result.put("message", "관리자만 등록할 수 있습니다.");
			return result;
		}
		
		// 공연 데이터 삽입
		int inserted = performanceDao.insertPerformance(performance);
		result.put("success", inserted > 0);
		return result;
	}
}


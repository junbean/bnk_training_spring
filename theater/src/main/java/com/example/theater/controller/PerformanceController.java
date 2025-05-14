package com.example.theater.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.theater.dao.IPerformanceDao;
import com.example.theater.dao.IReservationDao;
import com.example.theater.dao.IReviewDao;
import com.example.theater.dto.MemberDTO;
import com.example.theater.dto.PerformanceDTO;
import com.example.theater.dto.ReviewDTO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/performance")
public class PerformanceController {
    private final IPerformanceDao performanceDao;
    private final IReservationDao reservationDao;
    private final IReviewDao reviewDao;

	@Autowired
	public PerformanceController(IPerformanceDao pDao, IReservationDao reservationDao, IReviewDao reviewDao) {
		this.performanceDao = pDao;
        this.reservationDao = reservationDao;
        this.reviewDao = reviewDao;
	}
	

	@GetMapping("/detail")
	public String getPerformanceDetail(
		@RequestParam("id") int performanceId, 
        HttpSession session,
		Model model
	) {
        // 공연 정보 조회
		PerformanceDTO performance = performanceDao.getPerformanceById(performanceId);
		model.addAttribute("performance", performance);

        // 로그인 사용자 확인 및 예매 여부 체크
        MemberDTO user = (MemberDTO) session.getAttribute("loginUser");
        boolean alreadyReserved = false;
        if (user != null) {
            int count = reservationDao.countReservationByUserAndPerformance(user.getUserId(), performanceId);
            alreadyReserved = (count > 0);
        }
        model.addAttribute("alreadyReserved", alreadyReserved);
        
	    // 3. 후기 목록 추가
	    List<ReviewDTO> reviewList = reviewDao.getReviewsByPerformance(performanceId);
	    model.addAttribute("reviewList", reviewList);
			
		return "performance/performanceDetail";
	}
	

	@GetMapping("/list")
	public String getPerformanceList(
		@RequestParam("page") int page, 
		Model model
	) {
		List<PerformanceDTO> performanceList = performanceDao.getPerformanceList();
		model.addAttribute("performanceList", performanceList);
        return "performance/performanceList";
	}
	
	
	
	/*
	
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
}

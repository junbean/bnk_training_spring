package com.example.theater.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.theater.dao.IReservationDao;
import com.example.theater.dto.MemberDTO;
import com.example.theater.dto.ReservationDTO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
	private IReservationDao reservationDao;

	@Autowired
	public ReservationController(IReservationDao dao) {
		reservationDao = dao;
	}
	
	// 예매 등록
	@PostMapping("/create")
    public Map<String, Object> createReservation(
    	@RequestBody ReservationDTO reservation, 
    	HttpSession session
    ) {
        Map<String, Object> result = new HashMap<>();
        // 세션에서 로그인 사용자 확인
        MemberDTO user = (MemberDTO) session.getAttribute("loginUser");
        // 로그인된 사용자인지 판별
        if (user == null) {
            result.put("success", false);
            result.put("message", "로그인이 필요합니다.");
            return result;
        }

        // 유저 ID 강제 설정
        reservation.setUserId(user.getUserId());

        // 1. 좌석 감소
        int updated = reservationDao.decreaseRemainingSeats(reservation.getPerformanceId());
        if (updated == 0) {
            result.put("success", false);
            result.put("message", "좌석이 없습니다.");
            return result;
        }

        // 2. 예매 등록
        int inserted = reservationDao.insertReservation(reservation);
        result.put("success", inserted > 0);
        return result;
    }
	
	
	// 사용자 예매 내역 조회
    @GetMapping("/mylist")
    public List<ReservationDTO> getMyReservations(HttpSession session) {
        MemberDTO user = (MemberDTO) session.getAttribute("loginUser");
        if (user == null) return Collections.emptyList();
        return reservationDao.getReservationsByUser(user.getUserId());
    }
}

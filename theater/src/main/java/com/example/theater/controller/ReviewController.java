package com.example.theater.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.theater.dao.IReviewDao;
import com.example.theater.dto.MemberDTO;
import com.example.theater.dto.ReviewDTO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/review")
public class ReviewController {
	private final IReviewDao reviewDao;

	public ReviewController(IReviewDao dao) {
		this.reviewDao = dao;
	}
	
	// 후기 등록
	@PostMapping("/create")
    public Map<String, Object> createReview(@RequestBody ReviewDTO review, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        MemberDTO user = (MemberDTO) session.getAttribute("loginUser");

        if (user == null) {
            result.put("success", false);
            result.put("message", "로그인이 필요합니다.");
            return result;
        }

        review.setUserId(user.getUserId());

        int inserted = reviewDao.insertReview(review);
        result.put("success", inserted > 0);
        return result;
    }
	
    
    // 후기 수정
    @PutMapping("/update")
    public Map<String, Object> updateReview(@RequestBody ReviewDTO review, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        MemberDTO user = (MemberDTO) session.getAttribute("loginUser");

        if (user == null || !user.getUserId().equals(review.getUserId())) {
            result.put("success", false);
            result.put("message", "권한이 없습니다.");
            return result;
        }

        int updated = reviewDao.updateReview(review);
        result.put("success", updated > 0);
        return result;
    }

    // 후기 삭제
    @DeleteMapping("/delete")
    public Map<String, Object> deleteReview(@RequestParam("reviewId") int reviewId, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        MemberDTO user = (MemberDTO) session.getAttribute("loginUser");

        if (user == null) {
            result.put("success", false);
            result.put("message", "로그인이 필요합니다.");
            return result;
        }

        int deleted = reviewDao.deleteReview(reviewId, user.getUserId());
        result.put("success", deleted > 0);
        return result;
    }
    
}

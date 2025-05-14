package com.example.theater.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.theater.dao.IQnaDao;
import com.example.theater.dto.MemberDTO;
import com.example.theater.dto.QnaDTO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/qna")
public class QnaController {
	private final IQnaDao qnaDao;
	
	@Autowired
	public QnaController(IQnaDao dao) {
		qnaDao = dao;
	}
	
	
	// 1. 사용자 질문 등록
	@PostMapping("/create")
	public Map<String, Object> createQuestion(@RequestBody QnaDTO qna, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
	
		MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
		if(loginUser == null) {
			result.put("success", false);
			result.put("message", "로그인이 필요합니다.");
			return result;
		}
		
		qna.setUserId(loginUser.getUserId());
		int inserted = qnaDao.insertQuestion(qna);
		result.put("success", inserted > 0);
		return result;
	}
	
	// 2. 전체 Qna 목록 조회
	
	// 3. Qna 상세 조회
	
	// 4. 관리자 답변 등록
	@PutMapping("/answer")
	public Map<String, Object> registerAnswer(@RequestBody Map<String, Object> payload, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
	
		MemberDTO adminUser = (MemberDTO) session.getAttribute("loginUser");
        if (adminUser == null || !"admin".equals(adminUser.getUserId())) { // 혹은 isAdmin 체크
            result.put("success", false);
            result.put("message", "관리자 권한이 필요합니다.");
            return result;
        }

        Long qnaId = Long.valueOf(payload.get("qnaId").toString());
        String answer = payload.get("answerContent").toString();

        int updated = qnaDao.updateAnswer(qnaId, answer, adminUser.getUserId());
        result.put("success", updated > 0);
        return result;
	}
	
}

package com.example.theater.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.theater.dto.QnaDTO;

@Mapper
public interface IQnaDao {
	// 질문 등록
	int insertQuestion(@Param("q") QnaDTO qna);
	
	// 전체 QnA 리스트
	List<QnaDTO> getAllQnaList();
	
	// 사용자별 QnA
	List<QnaDTO> getQnaListByUser(@Param("userId") String userId);
	
	// 상세 조회
	QnaDTO getQnaById(@Param("qnaId") Long qnaId);
	
	// 관리자 답변 등록
	int updateAnswer(
		@Param("qnaId") Long qnaId,
		@Param("answer") String answerContent,
		@Param("adminId") String adminId
	);
	
	// 관리자 답변 삭제
	int deleteAnswer(@Param("qnaId") Long qnaId);
	
	// 답변 상태 업데이트 - 이건 잘 안 쓸듯
	int updateStatus(
		@Param("qnaId") Long qnaId,
		@Param("status") String status
	);
}

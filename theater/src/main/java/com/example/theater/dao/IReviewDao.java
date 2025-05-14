package com.example.theater.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.theater.dto.ReviewDTO;

@Mapper
public interface IReviewDao {
    // 후기 등록
    int insertReview(@Param("r") ReviewDTO review);

    // 공연별 후기 조회
    List<ReviewDTO> getReviewsByPerformance(@Param("performanceId") int performanceId);

    // 후기 수정
    int updateReview(@Param("r") ReviewDTO review);

    // 후기 삭제
    int deleteReview(@Param("reviewId") int reviewId, @Param("userId") String userId);

    // 사용자 본인의 후기 목록 (마이페이지)
    List<ReviewDTO> getReviewsByUser(@Param("userId") String userId);
}

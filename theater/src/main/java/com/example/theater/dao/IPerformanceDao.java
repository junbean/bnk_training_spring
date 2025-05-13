package com.example.theater.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.theater.dto.PerformanceDTO;

@Mapper
public interface IPerformanceDao {
	// 공연 전체 조회
	List<PerformanceDTO> getPerformanceList();
	
	// 현재 상영 예정 공연 조회
	List<PerformanceDTO> getPerformanceUpComingList();
	
	// 현재 상영 중인 공연 조회
	List<PerformanceDTO> getPerformanceOnGoingList();
	
	// 현재 상영 종료 공연 조회
	List<PerformanceDTO> getPerformanceClosedList();	
	
	
	// 선택 공연 조회 - 상세
	PerformanceDTO getPerformanceById(
		@Param("performanceId") int performanceId
	);
	
	
	// 공연 데이터 삽입
	int insertPerformance(
		@Param("p") PerformanceDTO performanceDTO
	);
}

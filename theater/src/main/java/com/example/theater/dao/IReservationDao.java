package com.example.theater.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.theater.dto.ReservationDTO;

@Mapper
public interface IReservationDao {
	// 예매 등록 (INSERT)
	int insertReservation(@Param("r") ReservationDTO reservation);
	// 사용자별 내역 조회 (SELECT)
	List<ReservationDTO> getReservationsByUser(String userId);
	// 공연 예매 시 잔여 좌석 감소(UPDATE) - 예매 등록과 연계
	int decreaseRemainingSeats(Long performanceId);
	// 예매 취소 기능 (선택) (DELETE)
	int cancelReservation(int reservationId);
	// 예매 취소 시 좌석 증가 
	int increaseRemainingSeats(int performanceId);
	// 사용자가 해당 공연을 예매했는지 여부 확인 (1건 존재 여부)
	int countReservationByUserAndPerformance(@Param("userId") String userId, @Param("performanceId") int performanceId);

}

package com.example.theater.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReservationDTO {
    private Long reservationId;       // 예매 ID
    private String userId;            // 사용자 ID
    private Long performanceId;       // 공연 ID
    private Date reservationDate;     // 예매 일시
    private int totalPrice; 
}

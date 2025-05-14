package com.example.theater.dto;

import java.util.Date;

import lombok.Data;

@Data
public class QnaDTO {
	private Long qnaId;
	private String userId;
	private String questionContent;
	private Date questionDate;
	private String answerContent;
	private Date answerDate;
	private String answerAdminId;
	private String status;			// WAITING, ANSWERED
}

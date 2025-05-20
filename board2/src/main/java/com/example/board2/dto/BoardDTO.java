package com.example.board2.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BoardDTO {
	private int bno;
	private String title;
	private String content;
	private String writer;
	private Date date;
}

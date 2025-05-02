package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDTO {
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String grade;
}

package com.example.theater.dto;

import lombok.Data;

@Data
public class MemberDTO {
	private String userId;
	private String password;
	private String name;
	private String phone;
    private boolean admin;
}

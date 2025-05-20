package com.example.session.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
	@NotNull(message = "아이디는 필수 입력값입니다.")
	private String id;
	// @NotNull(message = "pw is null")
	// @Size(min=3, max=10, message = "pw min 3, max 10")
	private String pw;
	private String name;
	private String role;
}

package com.example.securityTest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tbl_user")
@Data
public class UserEntity {
	@Id
	private String username;
	private String password;
	private String name;
	private String role;
}

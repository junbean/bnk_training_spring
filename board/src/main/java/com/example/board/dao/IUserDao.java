package com.example.board.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.board.dto.UserDTO;

@Mapper
public interface IUserDao {
	UserDTO selectToLogin(
		@Param("userId") String userId, 
		@Param("password") String password
	);
	int insert(@Param("u") UserDTO user);
}

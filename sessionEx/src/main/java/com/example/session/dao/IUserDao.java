package com.example.session.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.session.dto.UserDto;

@Mapper
public interface IUserDao {
	UserDto login(@Param("id") String id, @Param("pw") String pw);
	
	List<UserDto> getUserList();
	
	List<UserDto> getUserListWithParam(@Param("user") UserDto user);
	
	List<UserDto> getUserListWithParam2(@Param("user") UserDto user);
}

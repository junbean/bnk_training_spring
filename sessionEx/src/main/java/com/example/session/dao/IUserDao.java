package com.example.session.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.session.dto.UserDto;

@Mapper
public interface IUserDao {
	UserDto login(@Param("id") String id, @Param("pw") String pw);
}

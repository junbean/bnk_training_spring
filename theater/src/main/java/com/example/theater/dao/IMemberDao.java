package com.example.theater.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.theater.dto.MemberDTO;

@Mapper
public interface IMemberDao {
	MemberDTO getMemberbyLogin(
		@Param("userId") String userId, 
		@Param("password") String password
	);
	
	int checkUserIdExists(String userId);
	
	int insertMember(@Param("m") MemberDTO member);		
}
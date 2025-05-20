package com.example.board2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.board2.dto.MemberDTO;

@Mapper
public interface IMemberDao {
	List<MemberDTO> getMemberList();
	
	MemberDTO checkMember();
	
	int insertMember();
}

package com.example.talend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.talend.dto.MemberDTO;

@Mapper
public interface IMemberDao {
	List<MemberDTO> getMemberList();
	MemberDTO getMember(); 
	int memberRegist(@Param("m") MemberDTO member);
}

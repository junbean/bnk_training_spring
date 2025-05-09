package com.example.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.mybatis.dto.MemberDTO;


@Mapper
public interface IMemberDao {
	
	// @Select("SELECT * FROM tbl_member")
	List<MemberDTO> getList();
	
	// @Select("SELECT * FROM tbl_member WHERE id = #{id}")
	MemberDTO getMember(@Param("id") String id);
	
	// @Update("UPDATE tbl_member SET pw=?, name=?, phone=?, grade=? WHERE id = ?")
	// @Update("UPDATE tbl_member SET pw=#{m.pw}, name=#{m.name}, phone=#{m.phone}, grade=#{m.grade} WHERE id = #{m.id}")
	void update(@Param("m") MemberDTO member);

	// @Delete("DELETE FROM tbl_member WHERE id = #{id}")
	void delete(@Param("id") String id);

	// @Insert("INSERT INTO tbl_member VALUES(#{m.id}, #{m.pw}, #{m.name}, #{m.phone}, #{m.grade})")
	void insert(@Param("m") MemberDTO member);
}

/*
https://mybatis.org/mybatis-3/getting-started.html


*/
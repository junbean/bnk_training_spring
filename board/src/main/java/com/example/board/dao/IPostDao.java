package com.example.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.board.dto.PostDTO;

@Mapper
public interface IPostDao {
	List<PostDTO> selectToList();
	PostDTO selectToOne(@Param("postId") int postId);
	int insert(@Param("p") PostDTO postDTO);
}

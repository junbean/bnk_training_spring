package com.example.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.board.dto.PostDTO;

@Mapper
public interface IPostDao {
	// 전체 게시글 리스트로 반환
	List<PostDTO> selectToList();
	
	// 게시글 페이징
	List<PostDTO> selectToPage(
		@Param("start") int init, 
		@Param("end") int end
	);
	
	// 특정 게시글 찾기 - postId를 통해
	PostDTO selectToOne(
		@Param("postId") int postId
	);
	
	// 전체 게시글 수 
	int getTotalPostCount();
	
	// 게시글 삽입
	// @Insert("INSERT INTO posts VALUES(seq_post_id.NEXTVAL, #{p.title), #{p.content}, #{p.authorId}")
	int insert(
		@Param("p") PostDTO postDTO
	);

	
}

package com.example.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.board.dto.CommentDTO;

@Mapper
public interface ICommentDao {
	// 게시글번호를 통한 댓글 조회
	List<CommentDTO> selectCommentsByPostId(Long PostId);

	// 댓글 추가
	int insertComment(@Param("c") CommentDTO comment);
	
	// 댓글 삭제
	int deleteComment(Long commentId);
}

package com.example.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.dao.ICommentDao;
import com.example.board.dto.CommentDTO;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	
	private ICommentDao commentDao;
	
	@Autowired
	public CommentController(ICommentDao dao) {
		commentDao = dao;
	}
	
	// 댓글 작성
	@GetMapping("/post/{postId}")
	public List<CommentDTO> getCommentList(@PathVariable("postId") Long postId) {
		List<CommentDTO> commentList = commentDao.selectCommentsByPostId(postId);
		return commentList;
	}
	
	@PostMapping("/writeComment")
	public int writeComment() {
		
	}
	
}

// https://claude.ai/chat/78b70c1c-44b5-4074-ae83-57e48572d7ed

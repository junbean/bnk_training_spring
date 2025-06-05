package com.example.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.dto.BoardDTO;
import com.example.rest.entity.BoardEntity;
import com.example.rest.service.BoardService;

@RestController
@RequestMapping("/api/board-post")
public class PostBoardController {
	
	@Autowired
	private BoardService boardService;
	
	// rest api 원칙
	// 기능을 메서드명으로 한다
	
	// board자료를 post한다
	@PostMapping("/board")
	public ResponseEntity<BoardEntity> postBoard(@RequestBody BoardDTO boardDTO) {
		System.out.println(boardDTO);
		BoardEntity result = boardService.registBoard(boardDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
}

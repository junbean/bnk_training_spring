package com.example.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.dto.BoardDTO;
import com.example.rest.entity.BoardEntity;
import com.example.rest.service.BoardService;

@RestController
@RequestMapping("/api/board-put")
public class PutBoardController {
	
	@Autowired
	private BoardService boardService;

	// 반환시에 regDate는 null값을 가진다
	@PutMapping("/board")
	public ResponseEntity<BoardEntity> putBoard(@RequestBody BoardDTO boardDTO) {
		BoardEntity result = boardService.updateBoard(boardDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
}

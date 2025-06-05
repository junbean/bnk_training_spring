package com.example.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.entity.BoardEntity;
import com.example.rest.service.BoardService;

@RestController
@RequestMapping("/api/board-get")
public class GetBoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	// bno를 통한 조회 api 구현
	@GetMapping("/board/{bno}")
	public ResponseEntity<BoardEntity> searchBoard(@PathVariable("bno") Integer bno) {
		BoardEntity result = boardService.searchBoardByBno(bno);
		
		// legacy - 단순 반환
		// return ResponseEntity.status(HttpStatus.OK).body(result);
		
		
		// reuslt null 예외 처리
		if(result != null) {
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} 
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
}

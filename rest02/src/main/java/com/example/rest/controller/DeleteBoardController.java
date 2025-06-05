package com.example.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.service.BoardService;

@RestController
@RequestMapping("/api/board-delete")
public class DeleteBoardController {
	@Autowired
	private BoardService boardService;

	@DeleteMapping("/board/{bno}")
	public ResponseEntity<Void> deleteBoard(@PathVariable("bno") Integer bno) {
		boardService.deleteBoard(bno);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}

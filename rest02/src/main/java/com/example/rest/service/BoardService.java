package com.example.rest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rest.dto.BoardDTO;
import com.example.rest.entity.BoardEntity;
import com.example.rest.repository.BoardRepository;
import com.example.rest.translate.BoardTranslate;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private BoardTranslate boardTranslate;
	

	// 게시글 등록업무(기능=함수) 
	// registBoard : 준비 - 게시글, 결과
	public BoardEntity registBoard(BoardDTO boardDTO) {
		// DTO -> Entity 변환작업
		BoardEntity entity = boardTranslate.boardDtoToEntity(boardDTO);
		/*
		legacy 코드 
		BoardEntity entity = new BoardEntity();
		entity.setTitle(boardDTO.getTitle());
		entity.setContent(boardDTO.getContent());
		entity.setWriter(boardDTO.getWriter());
		*/
		BoardEntity result = boardRepository.save(entity);
		
		return result;
	}
	
	// 특정 bno를 통한 조회 기능 - select
	public BoardEntity searchBoardByBno(Integer bno) {
		BoardEntity result = boardRepository.findByBno(bno);
		return result;
	}
	
	// 게시글 수정 업무 - update
	// 준비 - 수정된 게시글
	// 결과 - ?
	public BoardEntity updateBoard(BoardDTO boardDTO) {
		// DTO -> Entity 변환 작업
		BoardEntity entity = boardTranslate.boardDtoToEntity(boardDTO);
		/*
		legacy 코드 
		BoardEntity entity = new BoardEntity();
		entity.setBno(boardDTO.getBno());
		entity.setTitle(boardDTO.getTitle());
		entity.setContent(boardDTO.getContent());
		entity.setWriter(boardDTO.getWriter());
		*/
		return boardRepository.save(entity);
	}
	
	public void deleteBoard(Integer bno) {
		boardRepository.deleteById(bno);
	}
	
}






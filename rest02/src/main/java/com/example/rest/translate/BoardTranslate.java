package com.example.rest.translate;

import org.springframework.stereotype.Component;

import com.example.rest.dto.BoardDTO;
import com.example.rest.entity.BoardEntity;

@Component
public class BoardTranslate {
	public BoardDTO boardEntityToDto(BoardEntity entity) {
		BoardDTO dto = new BoardDTO();
		dto.setBno(entity.getBno());
		dto.setTitle(entity.getTitle());
		dto.setContent(entity.getContent());
		dto.setWriter(entity.getWriter());
		
		return dto;
	}
	
	public BoardEntity boardDtoToEntity(BoardDTO dto) {
		BoardEntity entity = new BoardEntity();
		entity.setBno(dto.getBno());
		entity.setTitle(dto.getTitle());
		entity.setContent(dto.getContent());
		entity.setWriter(dto.getWriter());

		return entity;
	}
}

package com.example.exam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.exam.dto.QuestionDTO;

@Mapper
public interface QuestionDAO {
	List<QuestionDTO> getQuestions();
	QuestionDTO findById(int id);
}

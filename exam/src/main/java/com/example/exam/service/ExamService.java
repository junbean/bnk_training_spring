package com.example.exam.service;

import java.util.List;
import java.util.Map;

import com.example.exam.dto.QuestionDTO;

public interface ExamService {
    List<QuestionDTO> getQuestions();
    QuestionDTO getQuestionById(int questionId);
    int calculateScore(Map<Integer, String> userAnswers);
}

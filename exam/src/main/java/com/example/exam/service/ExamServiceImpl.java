package com.example.exam.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.exam.ExamApplication;
import com.example.exam.dao.QuestionDAO;
import com.example.exam.dto.QuestionDTO;

@Service
public class ExamServiceImpl implements ExamService {

	private final QuestionDAO questionDAO; 
	
	@Autowired
	public ExamServiceImpl(QuestionDAO dao, ExamApplication examApplication) {
		questionDAO = dao;
	}

	@Override
	public List<QuestionDTO> getQuestions() {
		return questionDAO.getQuestions();
	}
	
	@Override
	public QuestionDTO getQuestionById(int questionId) {
		return questionDAO.findById(questionId);
	}
	
	@Override
	public int calculateScore(Map<Integer, String> userAnswers) {
	    int correct = 0;
	    for (Map.Entry<Integer, String> entry : userAnswers.entrySet()) {
	        QuestionDTO q = questionDAO.findById(entry.getKey());
	        if (q.getCorrectAnswer().equals(entry.getValue())) {
	            correct++;
	        }
	    }
	    return correct;
	}
}

  
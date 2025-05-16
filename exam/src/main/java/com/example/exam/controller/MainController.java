package com.example.exam.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.exam.dto.QuestionDTO;
import com.example.exam.service.ExamService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {
	private ExamService examService;
	
	@Autowired
	public MainController(ExamService service) {
		examService = service;
	}

    // 1. 홈 페이지
    @GetMapping("/")
    public String homePage() {
        return "index"; 
    }
    
    // 2. 시험 시작 페이지
    @GetMapping("/exam")
    public String startExam(Model model) {
        List<QuestionDTO> questionList = examService.getQuestions();
        model.addAttribute("questions", questionList);
        return "exam";
    }
    
    // 3. 시험 제출
    @PostMapping("/exam/submit")
    public String submitExam(HttpServletRequest request, Model model) {
        Map<Integer, String> userAnswers = new HashMap<>();
        String[] questionIds = request.getParameterValues("questionId");

        List<Map<String, Object>> resultList = new ArrayList<>();
        int correctCount = 0;

        for (String qid : questionIds) {
            int questionId = Integer.parseInt(qid);
            String userAnswerNum = request.getParameter("answer" + qid);

            QuestionDTO question = examService.getQuestionById(questionId);
            String correctAnswerNum = question.getCorrectAnswer();

            // 보기 배열
            String[] options = {
                question.getOption1(),
                question.getOption2(),
                question.getOption3(),
                question.getOption4()
            };

            // 정답 보기 텍스트
            int correctIdx = Integer.parseInt(correctAnswerNum) - 1;
            String correctAnswerText = options[correctIdx];

            // 기본값 설정
            boolean isCorrect = false;
            String userAnswerText = "미응답";

            // 사용자가 응답했을 경우만 처리
            if (userAnswerNum != null && !userAnswerNum.isBlank()) {
                int userIdx = Integer.parseInt(userAnswerNum) - 1;
                userAnswerText = options[userIdx];
                isCorrect = correctAnswerNum.equals(userAnswerNum);
                if (isCorrect) correctCount++;
            }

            // 결과 저장
            Map<String, Object> resultRow = new HashMap<>();
            resultRow.put("questionText", question.getQuestionText());
            resultRow.put("correctAnswer", correctAnswerText);
            resultRow.put("userAnswer", userAnswerText);
            resultRow.put("result", isCorrect ? "정답" : "오답");
            resultRow.put("explanation", question.getExplanation());

            resultList.add(resultRow);
        }

        // 점수 계산
        int total = questionIds.length;
        double percentage = (double) correctCount / total * 100;
        boolean isPass = percentage >= 60.0;

        String passStatus = isPass
                ? "🎉 <span style='color:green;'>합격입니다!</span>"
                : "😢 <span style='color:red;'>불합격입니다.</span>";

        // 모델 전달
        model.addAttribute("score", correctCount);
        model.addAttribute("total", total);
        model.addAttribute("resultList", resultList);
        model.addAttribute("passStatus", passStatus);

        return "result";
    }
    
    /*
    @PostMapping("/exam/submit")
    public String submitExam(HttpServletRequest request, Model model) {
        Map<Integer, String> userAnswers = new HashMap<>();
        String[] questionIds = request.getParameterValues("questionId");

        List<Map<String, Object>> resultList = new ArrayList<>();
        int correctCount = 0;

        for (String qid : questionIds) {
            int questionId = Integer.parseInt(qid);
            String userAnswerNum = request.getParameter("answer" + qid);

            QuestionDTO question = examService.getQuestionById(questionId);
            String correctAnswerNum = question.getCorrectAnswer();

            boolean isCorrect = correctAnswerNum.equals(userAnswerNum);
            if (isCorrect) correctCount++;

            String[] options = {
                question.getOption1(),
                question.getOption2(),
                question.getOption3(),
                question.getOption4()
            };

            int correctIdx = Integer.parseInt(correctAnswerNum) - 1;
            int userIdx = Integer.parseInt(userAnswerNum) - 1;

            Map<String, Object> resultRow = new HashMap<>();
            resultRow.put("questionText", question.getQuestionText());
            resultRow.put("correctAnswer", options[correctIdx]);
            resultRow.put("userAnswer", options[userIdx]);
            resultRow.put("result", isCorrect ? "정답" : "오답");
            resultRow.put("explanation", question.getExplanation());

            resultList.add(resultRow);
        }

        int total = questionIds.length;
        double percentage = (double) correctCount / total * 100;
        boolean isPass = percentage >= 60.0;

        String passStatus = isPass
                ? "🎉 <span style='color:green;'>합격입니다!</span>"
                : "😢 <span style='color:red;'>불합격입니다.</span>";

        model.addAttribute("score", correctCount);
        model.addAttribute("total", total);
        model.addAttribute("resultList", resultList);
        model.addAttribute("passStatus", passStatus);

        return "result";
    }
	*/
    
    /*
    @PostMapping("/exam/submit")
    public String submitExam(HttpServletRequest request, Model model) {
        Map<Integer, String> userAnswers = new HashMap<>();
        String[] questionIds = request.getParameterValues("questionId");

        List<Map<String, Object>> resultList = new ArrayList<>();
        int correctCount = 0;

        for (String qid : questionIds) {
            int questionId = Integer.parseInt(qid);
            String userAnswerNum = request.getParameter("answer" + qid); // "1" ~ "4"

            QuestionDTO question = examService.getQuestionById(questionId); // findById 호출
            String correctAnswerNum = question.getCorrectAnswer(); // "1" ~ "4"

            boolean isCorrect = correctAnswerNum.equals(userAnswerNum);
            if (isCorrect) correctCount++;

            // 보기 번호 → 보기 텍스트
            String[] options = {
                question.getOption1(),
                question.getOption2(),
                question.getOption3(),
                question.getOption4()
            };
            int correctIdx = Integer.parseInt(correctAnswerNum) - 1;
            int userIdx = Integer.parseInt(userAnswerNum) - 1;

            Map<String, Object> resultRow = new HashMap<>();
            resultRow.put("questionText", question.getQuestionText());
            resultRow.put("correctAnswer", options[correctIdx]);
            resultRow.put("userAnswer", options[userIdx]);
            resultRow.put("result", isCorrect ? "정답" : "오답");
            resultRow.put("explanation", question.getExplanation());

            resultList.add(resultRow);
        }

        model.addAttribute("score", correctCount);
        model.addAttribute("total", questionIds.length);
        model.addAttribute("resultList", resultList);

        return "result";
    }
    */
    
    /*
    @PostMapping("/exam/submit")
    public String submitExam(HttpServletRequest request, Model model) {
        Map<Integer, String> userAnswers = new HashMap<>();
        String[] questionIds = request.getParameterValues("questionId");

        List<Map<String, Object>> resultList = new ArrayList<>();
        int correctCount = 0;

        for (String qid : questionIds) {
            int questionId = Integer.parseInt(qid);
            String userAnswer = request.getParameter("answer" + qid);

            QuestionDTO question = examService.getQuestionById(questionId); // findById 호출
            String correctAnswer = question.getCorrectAnswer();

            boolean isCorrect = correctAnswer.equals(userAnswer);
            if (isCorrect) correctCount++;

            Map<String, Object> resultRow = new HashMap<>();
            resultRow.put("questionId", questionId);
            resultRow.put("correctAnswer", correctAnswer);
            resultRow.put("userAnswer", userAnswer);
            resultRow.put("result", isCorrect ? "정답" : "오답");

            resultList.add(resultRow);
        }

        model.addAttribute("score", correctCount);
        model.addAttribute("total", questionIds.length);
        model.addAttribute("resultList", resultList);

        return "result";
    }
    */
    /*
    @PostMapping("/exam/submit")
    public String submitExam(HttpServletRequest request, Model model) {
    	Map<Integer, String> userAnswers = new HashMap<>(); 
    	String[] questionIds = request.getParameterValues("questionId");
    	
    	for(String qid: questionIds) {
            String answer = request.getParameter("answer" + qid);
    		userAnswers.put(Integer.parseInt(qid), answer);
    	}

    	int score = examService.calculateScore(userAnswers);
    	model.addAttribute("score", score);
    	model.addAttribute("total", questionIds.length);
    	
    	return "result";
    }
    */
}

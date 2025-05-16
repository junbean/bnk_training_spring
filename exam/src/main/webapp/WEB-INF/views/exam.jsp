<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
    	<title>시험 문제</title>
    	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    	<style>
	        body {
	            background-color: #ffffff;
	            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	            display: flex;
	            flex-direction: column;
	            align-items: center;
	            justify-content: center;
	            min-height: 100vh;
	            margin: 0;
	        }
	
	        .container {
	            width: 700px;
	            padding: 40px;
	            margin: 20px; 
	            border: 1px solid #ddd;
	            border-radius: 12px;
	            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
	            background-color: #fff;
	        }
	
	        h2 {
	            text-align: center;
	            margin-bottom: 20px;
	            color: #333;
	        }
	
	        #timer {
	            text-align: center;
	            font-size: 18px;
	            color: red;
	            margin-bottom: 30px;
	        }
	
	        .question-block {
	            margin-bottom: 30px;
	        }
	
	        .question-block p {
	            font-weight: bold;
	            color: #333;
	        }
	
	        .question-block label {
	            display: block;
	            margin: 4px 0;
	            cursor: pointer;
	        }
	
	        input[type="submit"] {
	            background-color: #4CAF50;
	            color: white;
	            border: none;
	            padding: 12px 24px;
	            font-size: 16px;
	            border-radius: 6px;
	            cursor: pointer;
	            display: block;
	            margin: 30px auto 0 auto;
	        }
	
	        input[type="submit"]:hover {
	            background-color: #45a049;
	        }
	
	        hr {
	            border: none;
	            border-top: 1px solid #ccc;
	            margin-top: 20px;
	        }
	    </style>
	</head>
	<body>
		<div class="container">
	        <h2>📘 시험 문제 (총 5문항)</h2>
	        <p id="timer">남은 시간: 300초</p>
	
	        <form id="examForm" action="/exam/submit" method="post">
	            <c:forEach var="q" items="${questions}" varStatus="status">
	                <div class="question-block">
	                    <p>Q${status.index + 1}. ${q.questionText}</p>
	                    <label><input type="radio" name="answer${q.id}" value="1" required /> ${q.option1}</label>
	                    <label><input type="radio" name="answer${q.id}" value="2" /> ${q.option2}</label>
	                    <label><input type="radio" name="answer${q.id}" value="3" /> ${q.option3}</label>
	                    <label><input type="radio" name="answer${q.id}" value="4" /> ${q.option4}</label>
	                    <input type="hidden" name="questionId" value="${q.id}" />
	                    <hr>
	                </div>
	            </c:forEach>
	
	            <input type="submit" value="제출하기" />
	        </form>
	    </div>	
	
		<script>
			let timeLeft = 300;
			
			const timer = setInterval(function () {
				timeLeft--;
				$("#timer").text("남은 시간: " + timeLeft + "초");
				
				if(timeLeft <= 0) {
					clearInterval(timer);
					alert("⏰ 시간이 초과되어 자동 제출됩니다.");
					$("#examForm").submit();
				}
			}, 1000);
		</script>
	</body>
</html>

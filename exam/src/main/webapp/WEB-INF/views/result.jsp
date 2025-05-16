<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
    	<title>시험 결과</title>
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
	            text-align: center;
	        }
	
	        h2 {
	            color: #333;
	            margin-bottom: 20px;
	        }
	
	        p {
	            font-size: 16px;
	            color: #444;
	        }
	
		    table {
		        border-collapse: collapse;
		        width: 100%;
		        margin: 20px 0;
		        font-size: 14px; /* ✅ 여기 추가 */
		    }
		
		    th, td {
		        border: 1px solid #aaa;
		        padding: 8px; /* ✅ 패딩도 살짝 줄임 */
		        text-align: center;
			    min-width: 60px;	//
		    }
		    /*
		    .result-cell {
			    min-width: 60px;
			    white-space: nowrap;
			}
			*/
	
	        .correct {
	            color: green;
	            font-weight: bold;
	        }
	
	        .wrong {
	            color: red;
	            font-weight: bold;
	        }
	
	        .btn-group {
	            margin-top: 20px;
	        }
	
	        .btn-group a {
	            background-color: #4CAF50;
	            color: white;
	            text-decoration: none;
	            padding: 10px 20px;
	            margin: 0 10px;
	            border-radius: 6px;
	            display: inline-block;
	        }
	
	        .btn-group a:hover {
	            background-color: #45a049;
	        }
	        
	        .explanation-row {
	            text-align: left;
	            font-size: 13px;
	            background-color: #f9f9f9;
	        }
	        
	        .pass-result {
	            font-size: 18px;
	            font-weight: bold;
	        }
	        
	        .gray {
	            color: gray;
	        }
	    </style>
	</head>
	<body>
	    <div class="container">
	        <h2>✅ 시험 결과</h2>
	        <p>총 ${total}문제 중 <strong>${score}</strong>개 정답입니다.</p>
	        <p class="pass-result">
	            <c:out value="${passStatus}" escapeXml="false" />
	        </p>
	
	        <table>
	            <thead>
	                <tr>
	                    <th>문항</th>
	                    <th>문제 내용</th>
	                    <th>정답</th>
	                    <th>사용자 입력</th>
	                    <th>결과</th>
	                </tr>
	            </thead>
	            <tbody>
	                <c:forEach var="row" items="${resultList}" varStatus="status">
	                    <tr>
	                        <td>${status.index + 1}</td>
	                        <td>${row.questionText}</td>
	                        <td>${row.correctAnswer}</td>
	                        <td>
	                            <c:choose>
	                                <c:when test="${row.userAnswer == '미응답'}">
	                                    <span class="gray">미응답</span>
	                                </c:when>
	                                <c:otherwise>
	                                    ${row.userAnswer}
	                                </c:otherwise>
	                            </c:choose>
	                        </td>
	                        <td>
	                            <span class="${row.result == '정답' ? 'correct' : 'wrong'}">
	                                ${row.result}
	                            </span>
	                        </td>
	                    </tr>
	                    <c:if test="${not empty row.explanation}">
	                        <tr>
	                            <td colspan="5" class="explanation-row">
	                                💡 <strong>해설:</strong> ${row.explanation}
	                            </td>
	                        </tr>
	                    </c:if>
	                </c:forEach>
	            </tbody>
	        </table>
	
	        <div class="btn-group">
	            <a href="/">홈으로</a>
	            <a href="/exam">다시 풀기</a>
	        </div>
	    </div>
	</body>
</html>

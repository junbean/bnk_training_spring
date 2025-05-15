<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h2>📋 Q&A 목록</h2>
		<nav>
		  	<a href="/">홈</a>
		  	<a href="/performance/list">공연 목록</a>
		  	<a href="/qna">qna</a>
		  	
	        <!-- 로그인 상태에 따른 메뉴 표시 -->
	        <c:choose>
	            <c:when test="${empty loginUser}">
	                <a href="/login">로그인</a>
	                <a href="/regist">회원 가입</a>
	            </c:when>
	            <c:otherwise>
	                <a href="/mypage">마이페이지</a>
	                <a href="/logout">로그아웃</a>
	            </c:otherwise>
	        </c:choose>
		</nav>
		<hr>
		<table border = "1">
	        <thead>
	            <tr>
	                <th>번호</th>
	                <th>작성자</th>
	                <th>질문 내용</th>
	                <th>작성일</th>
	                <th>상태</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="qna" items="${qnaList}" varStatus="loop">
	                <tr>
	                    <td>${loop.index + 1}</td>
	                    <td>${qna.userId}</td>
	                    <td><a href="/qna/detail?id=${qna.qnaId}">${fn:substring(qna.questionContent, 0, 30)}...</a></td>
	                    <td><fmt:formatDate value="${qna.questionDate}" pattern="yyyy-MM-dd" /></td>
	                    <td>
	                        <c:choose>
	                            <c:when test="${qna.status eq 'ANSWERED'}">
	                                ✅ 답변 완료
	                            </c:when>
	                            <c:otherwise>
	                                ⏳ 대기 중
	                            </c:otherwise>
	                        </c:choose>
	                    </td>
	                </tr>
	            </c:forEach>
	        </tbody>
	    </table>
        <button onclick="location.href='/qna/qnaForm'">질문 작성</button>
	</body>
</html>
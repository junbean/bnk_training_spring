<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	    <title>${performance.title} - 공연 상세</title>
	</head>
	<body>
		<h2>${performance.title}</h2>
	    <img src="${performance.imageUrl}" alt="${performance.title}" width="200" height="280" />
	
	    <p><strong>장르:</strong> ${performance.genre}</p>
	    <p><strong>감독:</strong> ${performance.director}</p>
	    <p><strong>출연:</strong> ${performance.cast}</p>
	    <p><strong>상영 기간:</strong>
	        <fmt:formatDate value="${performance.startDate}" pattern="yyyy-MM-dd" />
	         ~ 
	        <fmt:formatDate value="${performance.endDate}" pattern="yyyy-MM-dd" />
	    </p>
	    <p><strong>관람 등급:</strong> ${performance.ageLimit}</p>
	    <p><strong>가격:</strong>
	        <fmt:formatNumber value="${performance.price}" type="number" groupingUsed="true" /> 원
	    </p>
	    <p><strong>설명:</strong></p>
	    <pre>${performance.description}</pre>
	</body>
</html>
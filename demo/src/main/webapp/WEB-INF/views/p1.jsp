<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String name = (String) request.getAttribute("name"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>P1 Page</h1>
	<hr>
	<p>name : ${name } </p>
	<p>age :  ${age }</p>
	
	
	<!-- 
		<h1>P1 Page</h1>
		<hr>
		<p>p1 페이지입니다</p>
		
		<p>param : ${param.name } </p>
		<p>name : ${name } </p>
		
		<p>model-attr : ${name } </p>
		
		<p>name : ${name } </p>
		<p>age :  ${age }</p>
	-->
</body>
</html>
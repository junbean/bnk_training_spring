<%@page import="com.example.demo.dto.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<MemberDTO> loginList = (List<MemberDTO>) request.getAttribute("loginList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>loginResult</h1>
	<hr>
	<div>
	<%
		if (loginList == null || loginList.isEmpty()) {
	%>
			<p>등록된 로그인 정보가 없습니다.</p>
	<%
		} else {
	%>
		<%
			for (MemberDTO member : loginList) {
		%>
				<ol>		
					<li>아이디: <%= member.getId() %></li>
					<li>비밀번호: <%= member.getPw() %></li>
					<li>이름: <%= member.getName() %></li>
				</ol>
				<hr>
		<%
			}
		%>
	<%
		}
	%>
	</div>
	
	${memberDTO }
	
	
</body>
</html>
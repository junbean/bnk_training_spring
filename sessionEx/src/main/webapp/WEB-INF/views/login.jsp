<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Login Form</h1>
	<hr>
	<div>${msg}
	</div>
	<form action="/login" method="post">
		ID : <input type="text" name="id" value="${userId}" placeholder="아이디는 aaa"><br>
		PW : <input type="password" name="pw" value="${userPw}" placeholder="패스워드는 1234"><br>
		<input type="radio" name="autoLogin" value="true">아이디 저장<br>
		<input type="submit" value="login">
	</form>
</body>
</html>
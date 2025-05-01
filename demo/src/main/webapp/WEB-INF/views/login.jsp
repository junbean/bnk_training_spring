<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>LOGIN PAGE</h1>
	<hr>
	<form action="loginResult" method="post">
		<div>
			<label for="id">아이디</label>
			<input type="text" id="id" name="id" />
		</div>
		<div>
			<label for="pw">패스워드</label>
			<input type="password" id="pw" name="pw"/>
		</div>
		<div>
			<label for="name">이름</label>
			<input type="text" id="name" name="name"/>
		</div>
		<input type="submit" value="등록">
	</form>
	
	<hr><!-- ==================================================================== -->
	
	<form action="p3" method="GET">
		<div>
			<label for="id">아이디</label>
			<input type="text" id="id" name="id" />
		</div>
		<div>
			<label for="pw">패스워드</label>
			<input type="password" id="pw" name="pw"/>
		</div>
		<input type="submit" value="등록">
	</form>
	
	<hr><!-- ==================================================================== -->
	
	<a href="p3/aaa/1234">PathVariable로 파라미터 처리하기</a>
</body>
</html>
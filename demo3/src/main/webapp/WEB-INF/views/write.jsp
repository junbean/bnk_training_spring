<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Write page</h1>
	<hr>
	<form action="write" method="POST">
		ID : <input type="text" name="id" placeholder="아이디 작성"><br>
		PW : <input type="text" name="pw" placeholder="패스워드 작성"><br>
		NAME : <input type="text" name="name" placeholder="이름 작성"><br>
		PHONE : <input type="text" name="phone" placeholder="연락처 작성"><br>
		GRADE : <input type="text" name="grade" placeholder="등급 작성"><br>
		<input type="submit" value="등록">
	</form>
</body>
</html>
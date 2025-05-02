<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Update page</h1>
	<hr>
	<form action="update" method="POST">
		ID : <input type="text" name="id" value="${member.id}" placeholder="아이디 작성" readonly ><br>
		PW : <input type="text" name="pw" value="${member.pw}" placeholder="패스워드 작성"><br>
		NAME : <input type="text" name="name" value="${member.name}" placeholder="이름 작성"><br>
		PHONE : <input type="text" name="phone"value="${member.phone}" placeholder="연락처 작성"><br>
		GRADE : <input type="text" name="grade"value="${member.grade}" placeholder="등급 작성"><br>
		<input type="submit" value="수정하기">
	</form>
</body>
</html>
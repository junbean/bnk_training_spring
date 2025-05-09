<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입</h1>
	<hr>
	<form action="/user/regist" method="POST">
		<table>
			<thead>
				<tr>
					<th>항목</th>
					<th>입력란</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="userId" required></td>
				</tr>
				<tr>
					<th>패스워드</th>
					<td><input type="password" name="password" required></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" name="name" required></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="text" name="email" required></td>
				</tr>
				<tr>
					<th>연락처</th>
					<td><input type="text" name="phone" required></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="회원가입하기">
						<input type="button" onclick="moveToLogin()" value="로그인으로">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	
	
	<script>
		function moveToLogin() {
			location.href="/user/loginForm";
		}
	</script>
</body>
</html>
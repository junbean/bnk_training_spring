<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인</h1>
	<hr>
	<form action="/user/login" method="POST">
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
					<td><input type="text" name="userId"></td>
				</tr>
				<tr>
					<th>패스워드</th>
					<td><input type="text" name="password"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="로그인하기">
						<input type="button" onclick="moveToRegist()" value="회원가입으로">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	
	<script>
		function moveToRegist() {
			location.href="/user/registForm";
		}
	</script>
</body>
</html>
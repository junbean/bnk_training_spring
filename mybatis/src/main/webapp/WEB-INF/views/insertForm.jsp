<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>Regist Page</h1>
		<hr>
		<form action="/insert" method="POST">
			<table>
				<thead>
					<tr>
						<th>항목</th>
						<th>입력란</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>ID</th>
						<td><input type="text" name="id"></td>
					</tr>
					<tr>
						<th>PW</th>
						<td><input type="password" name="pw"></td>
					</tr>
					<tr>
						<th>NAME</th>
						<td><input type="text" name="name"></td>
					</tr>
					<tr>
						<th>PHONE</th>
						<td><input type="text" name="phone"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="등록하기"></td>
					</tr>
				</tbody>
			</table>
			<!-- id : <input type="text" name="id"><br>
			pw : <input type="password" name="pw"><br>
			name : <input type="text" name="name"><br>
			phone : <input type="text" name="phone"><br>
			<input type="submit" value="등록하기"> -->
		</form>
	</body>
</html>
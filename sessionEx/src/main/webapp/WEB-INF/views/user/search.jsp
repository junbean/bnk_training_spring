<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User Search List</title>
	</head>
	<body>
		<h1>User Search List</h1>
		<hr>
		<form action="result2" method="get">
			이름 	: <input type="text" name="name" placeholder="이름 입력" value="alice"><br>
			역할 	: <input type="text" name="role" placeholder="역할 입력" value="MEMBER"><br>
			<input type="submit" value="검색">
		</form>
		<hr>
		<table border="1">
			<thead>
				<tr>
					<th>no</th>
					<th>id</th>
					<th>name</th>
					<th>role</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${list }" varStatus="status">
					<tr>
						<td>${status.count }</td>
						<td>${user.id }</td>
						<td>${user.name }</td>
						<td>${user.role }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Detail Page</h1>
	<hr>
	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>PW</th>
				<th>NAME</th>
				<th>PHONE</th>
				<th>GRADE</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${member.id}</td>
				<td>${member.pw}</td>
				<td>${member.name}</td>
				<td>${member.phone}</td>
				<td>${member.grade}</td>
			</tr>
		</tbody>
	</table>
	<a href="updatePage?id=${member.id}">수정 페이지로 이동</a>
</body>
</html>
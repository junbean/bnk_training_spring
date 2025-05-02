<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>List Page</h1>
	<hr>
	<table border="1">
		<thead>
			<tr>
				<th>no</th>
				<th>id</th>
				<th>name</th>
				<th>phone</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="member" items="${list}" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td><a href="detailPage?id=${member.id}">${member.id}</a></td>
					<td>${member.name }</td>
					<td>${member.phone }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
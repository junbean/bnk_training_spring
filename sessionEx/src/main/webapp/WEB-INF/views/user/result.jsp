<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>User Search Result</h1>
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
				<c:forEach var="user" items="${searchList }" varStatus="status">
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
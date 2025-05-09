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
	<h1>Member List</h1>
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
					<td>${status.count}</td>
					<td><a href="/detail?id=${member.id}">${member.id}</a></td>
					<td>${member.name}</td>
					<td>${member.phone}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<input type="button" onclick="insMember()" value="추가하기">
	
	<script>
		function insMember() {
			location.href="/insertForm";
		}
	</script>
</body>
</html>
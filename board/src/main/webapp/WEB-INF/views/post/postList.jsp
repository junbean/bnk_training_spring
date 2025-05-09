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
		<h1>게시글 목록</h1>
		<hr>
		<table border="1">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자ID</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="post" items="${postList}" varStatus="status">
					<tr>
						<td>${status.count }</td>
						<td><a href="/post/detail?postId=${post.postId}">${post.title }</a></td>
						<td>${post.authorId }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input type="button" onclick="moveToWrite()" value="글쓰기">
		<input type="button" onclick="moveToIndex()" value="첫화면으로">
	</body>
	
	<script>
		function moveToWrite() {
			location.href = "/post/writeForm";
		}
		
		function moveToIndex() {
			location.href = "/";
		}
	</script>
</html>
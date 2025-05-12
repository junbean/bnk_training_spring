<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style>
			th {
				width : 100px;
			}
			td {
				width : 300px;
			}
			#content_td {
				height : 300px;
			}
			
		</style>
	</head>
	<body>
		<h1>상세 페이지</h1>
		<hr>	
		<table border="1">
			<tbody>
				<tr>
					<th>게시글 번호</th>
					<td>${postInfo.postId }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${postInfo.title }</td>
				</tr>
				<tr>
					<th>작성자ID</th>
					<td>${postInfo.authorId }</td>
				</tr>
				<tr>
					<th>작성일자</th>
					<td><fmt:formatDate value="${postInfo.regDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${postInfo.viewCount }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td id="content_td">${postInfo.content }</td>
				</tr>
			</tbody>
		</table>
		
	</body>
</html>
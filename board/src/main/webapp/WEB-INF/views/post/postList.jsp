<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
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
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="post" items="${postList}" varStatus="status">
					<tr>
						<%-- <td>${status.count }</td> --%>
						<td>${post.postId }</td>
						<td><a href="/post/detail?postId=${post.postId}">${post.title }</a></td>
						<td>${post.authorId }</td>
						<td><fmt:formatDate value="${post.regDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    	<td>${post.viewCount }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">
		    <!-- 이전 페이지 블록으로 이동 -->
		    <c:if test="${hasPrevBlock}">
		        <a href="/post/list?page=${prevBlockPage}">◀</a>
		    </c:if>
		    
		    <!-- 페이지 번호 표시 -->
		    <c:forEach begin="${startPage}" end="${endPage}" var="pageNum">
		        <c:choose>
		            <c:when test="${pageNum == currentPage}">
		                <strong>${pageNum}</strong>
		            </c:when>
		            <c:otherwise>
		                <a href="/post/list?page=${pageNum}">${pageNum}</a>
		            </c:otherwise>
		        </c:choose>
		    </c:forEach>
		    
		    <!-- 다음 페이지 블록으로 이동 -->
		    <c:if test="${hasNextBlock}">
		        <a href="/post/list?page=${nextBlockPage}">▶</a>
		    </c:if>
		</div>
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
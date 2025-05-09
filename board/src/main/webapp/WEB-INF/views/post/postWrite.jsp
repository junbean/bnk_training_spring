<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시글 작성 페이지</h1>
	<hr>
	<form action="/post/write" method="post">
		<table>
			<tbody>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title"></td>
				</tr>
				<tr>
					<th>내용</th>
            		<td>
            			<textarea name="content" rows="10" cols="50"></textarea>
            		</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="게시글 등록"> 
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	
	<script>
	
	</script>
</body>
</html>
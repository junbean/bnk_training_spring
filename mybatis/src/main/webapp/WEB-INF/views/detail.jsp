<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Member Detail Page</h1>
	<hr>
	<form action="/modify" method="POST">
		<table>
			<thead>
				<tr>
					<th>항목</th>
					<th>값</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>ID</th>
					<td><input type="text" name="id" value="${member.id}" readonly></td>
				</tr>
				<tr>
					<th>PW</th>
					<td><input type="text" name="pw" value="${member.pw}" ></td>
				</tr>
				<tr>
					<th>NAME</th>
					<td><input type="text" name="name" value="${member.name}" ></td>
				</tr>
				<tr>
					<th>PHONE</th>
					<td><input type="text" name="phone" value="${member.phone}" ></td>
				</tr>
				<tr>
					<th>GRADE</th>
					<td><input type="text" name="grade" value="${member.grade}" ></td>
				</tr>
				<tr>
					<td><input type="submit" value="수정"></td>
					<td><input type="button" onclick="delMember()" value="삭제"></td>
				</tr>
			</tbody>
		</table>
	</form>
	
	<script>
		function delMember() {
			// alert("삭제 버튼 클릭");
			const paramId = document.querySelector('input[name=id]');
			location.href="/delete/" + paramId.value;
		}
	</script>
</body>
</html>
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
    <!-- 조건 -->
    <c:if test="${not empty alertMessage}">
        <script>
            alert("${alertMessage}"); // 큰따옴표 추가
            location.href = "/login";
        </script>
    </c:if>
    
    
	<h1>Login Page</h1>
	
	
	<hr>
	<form action="login" method="post">
		
		<c:if test="errorMessage">
			<p id="errorMessage" style="color: red;">${errorMessage}</p>
		</c:if>
		
		ID : <input type="text" name="id" required><br>
		PASSWORD : <input type="password" name="password" required><br>
		
		<input type="submit" value="submit">
	</form>
	
	<script>
		const $id = document.getElementById("id");
		const $password = document.getElementById("password");
		
		function login() {
			const id = $id.value;
			const password = $password.value;
		}
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Login Page</h1>
	<hr>
	<form action="regist" method="post">
		<div>
			<label for="id">ID : </label> 
			<input type="text" id="id" name="id">
			<!-- 폼안에 있는 button의 기본 타입은 submit으로 submit기능을 수행하지 않는다면 명시적으로 type을 button으로 해줘야한다 -->
			<button type="button" onclick="checkDuplicateId()">아이디 중복 확인</button>
		
			<div id="messageId"></div>
		</div>
		
		<div>
			<label for="pw">PW : </label> 
			<input type="password" id="pw" name="pw">
			<br>
			<label for="pwCheck">PW CHECK : </label> 
			<input type="password" id="pwCheck" onchange="checkPassword">
			
			<div id="messagePassword"></div>
		</div>
		
		<div>
			<label for="name">NAME : </label> 
			<input type="text" id="name" name="name">
		</div>
		
		<div>
			<label for="phone">PHONE : </label> 
			<input type="text" id="phone" name="phone">
		</div>
		
		<input type="submit" value="등록하기">
	</form>
	
	<script>
		const messageId = document.getElementById("messageId");
		const messagePassword = document.getElementById("messagePassword");
		const inputId = document.getElementById("id");
		const inputPassword = document.getElementById("pw");
		const inputPasswordCheck = document.getElementById("pwCheck");
		
		function checkDuplicateId() {
			const id = inputId.value;
			
			const xhr = new XMLHttpRequest();
			xhr.onload = function() {
				// 객체가 아닌 자료형그대로 반환될때 JSON.parse를 사용하면 자료형 그대로 변환해준다
		        const isDuplicated = JSON.parse(xhr.responseText);
		        
		        console.log(isDuplicated);
				console.log(typeof(isDuplicated));
		        
				// 이상하게 usDuplicated가 boolean이 아니면 그대로 true로 조건문이 작동하는듯함
		        if(isDuplicated) {
		            // true면 중복된 ID
		            messageId.innerHTML = "<span style='color: red;'>사용불가능한 아이디입니다</span>";
		        } else {
		            // false면 사용 가능한 ID
		            messageId.innerHTML = "<span style='color: green;'>사용가능한 아이디입니다</span>";
		        }
			};
			xhr.open('GET', "duplicateId?id=" + id);
			xhr.send()
		}
		
		function checkPassword() {
			pw = inputPassword.value;
			pwCheck = inputPasswordCheck.value;
			
			if(pw == pwCheck) {
			    messagePassword.innerHTML = "<span style='color: gree;'>비밀번호가 일치합니다</span>";
			} else {
				messagePassword.innerHTML = "<span style='color: red;'>비밀번호가 일치하지 않습니다</span>";
			}
		}
	</script>
</body>
</html>
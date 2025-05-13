<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h2>회원 등록</h2>
		<hr>
		<form action="/member/login" method="post">
			<table>
				<tbody>
					<tr>
						<th>아이디</th>
						<td><input type="text" id="userId" name="userId" placeholder="아이디 입력" oninput="idInputEvent()" required></td>
						<td><input type="button" id="checkIdButton" onclick="idValidCheck()" value="아이디 체크"></td>
					</tr>
					<tr>
						<td colspan="3">
							<span id="idMessage"></span>
						</td>
					</tr>
					<tr>
						<th>패스워드</th>
						<td><input type="password" id="password" name="password" placeholder="패스워드 입력" onInput="validatePassword()" required></td>
					</tr>
					<tr>
						<td colspan="3">
							<span id="passwordMessage"></span>
						</td>
					</tr>
					<tr>
						<th>패스워드 체크</th>
						<td><input type="password" id="passwordCheck" name="passwordCheck" placeholder="패스워드 체크 입력" onInput="checkPasswordMatch()" required></td>
					</tr>
					<tr>
						<td colspan="3">
							<span id="passwordCheckMessage"></span>
						</td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" id="name" name="name" placeholder="이름 입력" required></td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td><input type="text" id="phone" name="phone" placeholder="전화번호 입력" required></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" onclick="registMember()" value="회원가입">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		
		<script>
			// 입력 태그
			const userId = document.getElementById("userId");
			const password = document.getElementById("password");
			const passwordCheckInput = document.getElementById("passwordCheck");
			const name = document.getElementById("name");
			const phone = document.getElementById("phone");
			
			// 버튼 태그
			const checkIdButton = document.getElementById("checkIdButton");
			
			// 메세지 태그
			const idMessage = document.getElementById("idMessage");
			const passwordMessage = document.getElementById("passwordMessage");
			const passwordCheckMessage = document.getElementById("passwordCheckMessage");
			
			
			// 정규 표현식
			const idRegex = /^[a-zA-Z][a-zA-Z0-9]*$/;	// 영문자로 시작, 영문자 + 숫자만 허용, 최소 4~12자
			const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;

			// 기타 변수
			let idCheck = false;		// 아이디 중복 확인
			let passwordValid = false;          // 패스워드 정규식 유효성 통과 여부
			let passwordMatch = false;          // 패스워드 일치 여부
			const userIdMinLength = 8;	// 아이디 최소 길이
			
			// 아이디 중복 체크 
			function idValidCheck() {
				const userIdValue = userId.value.trim();
				
				// 아이디 길이 체크 = 8이상
				if(userIdValue.length < userIdMinLength) {
					idCheck = false;
					idMessage.style.color = "red";
					idMessage.innerText = "아이디의 최소 길이는 8이상 입니다.";		
					return;
				}
				
				// 아이디 정규 표현식 검사
				if (!idRegex.test(userIdValue)) {
					idCheck = false;
					idMessage.style.color = "red";
					idMessage.innerText = "아이디는 영문자로 시작하고, 영문자와 숫자 조합이어야 합니다.";
					return;
				}
				
				// 비동기 아이디 중복 체크 요청
				fetch("/member/checkId?userId=" + userIdValue)
				.then(res => res.json())
				.then(data => {
					if(data.exists) {
						idCheck = false;
						idMessage.style.color = "red";
						idMessage.innerText = "이미 사용 중인 아이디입니다.";		
					} else {
						idCheck = true;
						idMessage.style.color = "green";
						idMessage.innerText = "사용 가능한 아이디입니다.";			
					}
				})
				.catch(err => {
					console.error("중복 체크 오류", err);
					idMessage.style.color = "red";
					idMessage.innerText = "중복 체크 중 오류가 발생했습니다.";
				})
			}
			
			// 아이디 입력시
			function idInputEvent() {
				idCheck = false;
			}
			
			// 패스워드 입력시 - 패스워드 유효성 검사
			function validatePassword() {
				const passwordValue = password.value;
			
				if (passwordRegex.test(passwordValue)) {
					passwordValid = true;
					passwordMessage.style.color = "green";
					passwordMessage.innerText = "사용 가능한 패스워드입니다.";
				} else {
					passwordValid = false;
					passwordMessage.style.color = "red";
					passwordMessage.innerText = "비밀번호는 영문자, 숫자, 특수문자 포함 8자 이상이어야 합니다.";
				}
			
				checkPasswordMatch(); // 비밀번호 변경 시, 일치 여부도 재검사
			}
			
			// 패스워드 일치 확인 함수
			function checkPasswordMatch() {
				const passwordValue = password.value;
				const passwordCheckValue = passwordCheckInput.value;

				if (passwordValue === passwordCheckValue) {
					passwordMatch = true;
					passwordCheckMessage.style.color = "green";
					passwordCheckMessage.innerText = "패스워드가 일치합니다.";
				} else {
					passwordMatch = false;
					passwordCheckMessage.style.color = "red";
					passwordCheckMessage.innerText = "패스워드가 일치하지 않습니다.";
				}
			}
			
			// 회원 등록 - 입력문 null 체크, 기타 변수 true체크, fetch
			function registMember() {
				const userIdInput = userId.value.trim();
				const passwordInput = password.value.trim();
				const nameInput = name.value.trim();
				const phoneInput = phone.value.trim();

				if (!idCheck) {
					idMessage.style.color = "red";
					idMessage.innerText = "아이디 체크를 완료해주세요.";
					userId.focus();
					return;
				}
				
				if (!passwordValid) {
					passwordMessage.style.color = "red";
					passwordMessage.innerText = "유효한 패스워드를 입력해주세요.";
					password.focus();
					return;
				}

				if (!passwordMatch) {
					passwordCheckMessage.style.color = "red";
					passwordCheckMessage.innerText = "패스워드가 일치하지 않습니다.";
					passwordCheckInput.focus();
					return;
				}
				
				if (nameInput.length === 0) {
					alert("이름을 입력해주세요.");
					name.focus();
					return;
				}
				
				if (phoneInput.length === 0) {
					alert("전화번호를 입력해주세요.");
					phone.focus();
					return;
				}
				
				// 모든 조건 만족 -> 회원가입 요청
				const data = {
					userId : userIdInput,
					password : passwordInput,
					name : nameInput,
					phone: phoneInput
				}
				
				fetch("/member/regist", {
					method: "POST",
					headers: {
						"Content-Type": "application/json"
					},
					body: JSON.stringify(data)
				})
				.then(res => res.json())
				.then(result => {
					if(result.success) {
						alert("회원가입이 완료되었습니다. 로그인 페이지로 이동합니다.");
						window.location.href = "/login"; // 로그인 페이지로 이동	
					} else {
						alert("회원가입에 실패했습니다. 다시 시도해주세요.");
					}
				})
				.catch(err => {
					console.error("회원가입 오류", err);
					alert("회원가입 중 오류가 발생했습니다.");
				});
			}
		</script>
	</body>
</html>
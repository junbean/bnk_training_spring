<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h2>로그인</h2>
		<hr>
		<form action="/member/login" method="post">
			<table>
				<tbody>
					<tr>
						<th>아이디</th>
						<td><input type="text" id="userId" name="userId" placeholder="아이디 입력" required></td>
					</tr>
					<tr>
						<th>패스워드</th>
						<td><input type="password" id="password" name="password" placeholder="패스워드 입력" required></td>
					</tr>
					<tr>
						<td colspan="2">
							<span id="loginMessage"></span>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" onclick="loginMember()" value="로그인">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		
		<script>
            const userId = document.getElementById("userId");
            const password = document.getElementById("password");

            function loginMember() {
                userIdValue = userId.value;
                passwordValue = password.value;

                if (userIdValue === "") {
                    alert("아이디를 입력하세요");
                    userId.focus();
                    return;
                }

                if (passwordValue === "") {
                    alert("패스워드를 입력하세요");
                    password.focus();
                    return;
                }

                fetch("/member/login", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded",
                    },
                    body: new URLSearchParams({
                        userId: userIdValue,
                        password: passwordValue,
                    }),
                })
                .then((response) => response.json())
                .then((data) => {
                    if (data.success) {
                        location.href = "/";
                    } else {
                        alert("아이디 또는 패스워드가 틀렸습니다");
                    }
                })
                .catch((error) => {
                    console.error("에러 발생:", error); // 여기 콜론 다음에 쉼표 추가
                });
            }
        </script>
	</body>
</html>
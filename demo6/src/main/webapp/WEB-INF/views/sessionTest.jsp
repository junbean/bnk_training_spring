<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style>
			#sessionModal {
				display : none;
				position: fixed;
				z-index: 9999;
				left: 0;
				top: 0;
				width: 100%;
				height: 100%;
				background-color: rgba(0, 0, 0, 0.4);
			}
			
			#modalContent {
				background-color : white;
				margin: 20% auto;
				padding: 20px;
				width: 300px;
				text-align: center;
				border-radius: 10px;
			}
			
		</style>
	</head>
	<body>
		<!-- 
		<h1>session Test</h1>
		<hr>
		<p id="cnt">10초 후 자동으로 로그아웃 됩니다.</p>
		
		
		<script>
			let cnt = 0; // 카운트 변수
			const timeLimit = 10;
		    const $p = document.getElementById("cnt"); // p태그
		
		    // 반복적으로 비동기 함수를 실행시킨다
		    const intervalId = setInterval(() => {
		        cnt++;
				$p.innerText = (timeLimit - cnt) + "초 후 자동으로 로그아웃 됩니다.";
				// 자동 로그아웃
		        if(cnt === 10) {
		        	alert("시간 초과로 로그아웃 되었습니다.")
		        	location.href = "/logout";
		        }
		    }, 1000);
		</script>
		 -->

		<!-- ================================================================================== -->
		  
		<h1>세션 카운트다운 : <span id="timer">60</span>초</h1>
		<div id="sessionModal">
			<div id="modalContent">
				<p>세션이 곧 만료됩니다. 연장하시겠습니까?</p>
				<button onclick="extendSession()">연장</button>
				<button onclick="logout()">로그아웃</button>
			</div>
		</div>
		
		<script>
			let timeLeft = 60;
			let $timer = document.getElementById("timer");
			let modalShown = false;
			
			const interval = setInterval(() => {
				timeLeft--;
				$timer.innerText = timeLeft;
				
				if(timeLeft === 10 && !modalShown) {
					document.getElementById("sessionModal").style.display = "block";
					modalShown = true;
				}
				
				if(timeLeft <= 0) {
					clearInterval(initerval);
					logout();
				}
			}, 1000);
			
			function extendSession() {
				fetch("/extend-sesion", {
					method: "POST"
				})
				.then(() => {
					timeLeft = 60;
					modalShown = false;
					document.getElementById("sessionModal").style.display = "none";
				})
			}
			
			function logout() {
				window.location.href = "/logout";
			}
		</script>
	</body>
</html>
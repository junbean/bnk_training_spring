<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Session Timeout Example</title>
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
		<h1>Index </h1>
		<hr>
		<a href="/sessionTest">session Test</a><br>
		<a href="/login">로그인으로 이동</a>
	</body>
</html>
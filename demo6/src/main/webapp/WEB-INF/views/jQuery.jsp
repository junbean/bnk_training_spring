<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	</head>
	<body>
		<div id="demo">-----</div>
		<button onclick="f()"> jQuery-Ajax Get요청</button>
		<hr>
		<div id="demo2">-----</div>
		<button onclick="f2()"> jQuery-Ajax Post요청</button>
		<hr>
		<div id="demo3">-----</div>
		<button onclick="f3()"> jQuery-Ajax Get요청 - Request Param</button>
		<hr>
	
	
		<script>
			function f() {
				/*
				jQuery Ajax 기본 형태
				$.ajax({
					url : 
					type : 
					success : 
					error : 
				});
				*/
				$.ajax({
					url : '/api/data',
					type : 'GET',
					success : function(response) {
						console.log('GET 성공 : ', response);
						$('#demo').html(response);
					},
					error : function(xhr) {
						console.log('GET 실패 : ', xhr);
						$('#demo').html(response);
					}
				});
			}		
			
			function f2() {
				$.ajax({
					url : '/api/data',
					type : 'POST',
					contentType : 'application/json',
					data : JSON.stringify({
						name : '홍길동',
						age : 30
					}),
					success : function(response) {
						console.log('POST(JSON) 성공 : ', response);
						$('#demo2').html(response);
					},
					error : function(xhr) {
						console.log('POST(JSON) 실패 : ', xhr);
					}
				})
			}
			
			function f3() {
				$.ajax({
					url : '/api/param?name=Andy&age=14',
					type : 'GET',
					success : function(response) {
						console.log('GET 성공 : ', response);
						$('#demo3').html(response);
					},
					error : function(xhr) {
						console.log('GET 실패 : ', xhr);
						$('#demo3').html(response);
					}
				});
			}
		</script>
	</body>
</html>
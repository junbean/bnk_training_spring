<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	</head>
	<body>
		<h1>Index</h1>
		<hr>
		<button onclick="run()">실행(GET-PATH)</button>
		<div id="demo">------</div>
		<hr>
		<button onclick="run1()">실행1(GET-PARAM)</button>
		<div id="demo1">------</div>
		<hr>
		<button onclick="run2()">실행2(POST-JSON)</button>
		<div id="demo2">------</div>
		<hr>
		<button onclick="run3()">실행3(POST-FORM)</button>
		<div id="demo3">------</div>
		
		<script>
			// GET 방식 요청 - pathVariable
			function run() {
				axios.get('/api/user/1')
				.then(response => {
					// 자바스크립트 객체로 변환
					console.log(response.data);
					document.getElementById("demo").innerHTML = response.data.id + ", " + response.data.name;
				})
				.catch(error => {
					document.getElementById("demo").innerHTML = error;
				});
			}
			
			// GET 방식 요청 - Param
			function run1() {
				axios.get('/api/user', {
					params: {
						id: 'hong',
						name: 'Hong gil dong'
					}
				})
				.then(response => {
					console.log(response.data);
					document.getElementById("demo1").innerHTML = response.data.id + ", " + response.data.name;
				})
				.catch(error => {
					document.getElementById("demo1").innerHTML = error;
				});
			}
			
			
			// post 요청 방식 - JS JSON 객체 전달 
			function run2() {
				axios.post('/api/userJson', {
					id: 'JD',
					name: 'James Dean'
				})
				.then(response => {
					console.log(response.data);
					document.getElementById("demo2").innerHTML = response.data.id + ", " + response.data.name;
				})
				.catch(error => {
					document.getElementById("demo2").innerHTML = error;
				});
			}
			

			// post 요청 방식 - Form Data -> parameter 전달
			function run3() {
				const formData = new FormData();
				formData.append("id", "ddd");
				formData.append("name", "Dennis");
				
				axios.post("/api/userForm", formData, {
					header: {
						'Content-Type': 'multipart/form-data'
					}
				})
				.then(response => {
					console.log("POST(FORM) 성공 : " + response.data);
					// document.getElementById("demo3").innerHTML = response.data.id + ", " + response.data.name;
					document.getElementById("demo3").innerHTML = "등록 완료";
				})
				.catch(error => {
					console.log("POST(FORM) 실패 : " + error);
					// document.getElementById("demo3").innerHTML = response.data.id + ", " + response.data.name;
					document.getElementById("demo3").innerHTML = "등록 실패";
				});
			}
		</script>
	</body>
</html>
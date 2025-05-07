<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>Index Page</h1>
		<hr>
		<h4>1. Ajax Get 요청</h4>
		<button onclick="getData()">1. Ajax Get 요청</button>
		<div id="demo1"> </div>
		
		<hr>
		
		<h4>2-1. Ajax Get 요청 2-1(User 정보 요청 : JSON 직접 작성)</h4>
		<button onclick="getUserData1()">2. Ajax Get 요청 2-1</button>
		<div id="demo2_1"> </div>
		
		<hr>
		
		<h4>2-2. Ajax Get 요청 2-2(User 정보 요청 : Jackson 라이브러리 활용)</h4>
		<button onclick="getUserData2()">2. Ajax Get 요청 2-2</button>
		<div id="demo2_2"> </div>
		
		<hr>
		
		<h4>3. Ajax Get 요청-3(파라미터 보내기)</h4>
		<button onclick="sendParameter()">3. Ajax Get 요청 3</button>
		<div id="demo3"> </div>
		
		<hr>
		
		<h4>4. Ajax Get 요청-4(파라미터 보내기 - 패스 이용)</h4>
		<button onclick="sendParameter2()">4. Ajax Get 요청 4</button>
		<div id="demo4"> </div>
		
		<hr>
		
		<h4>5. Ajax Get 요청-5(파라미터 보내기 - dto 이용)</h4>
		<button onclick="sendParameter3()">5. Ajax Get 요청 5</button>
		<div id="demo5"> </div>
		
		<hr>
		
		<h4>1. Ajax Post 요청</h4>
		<button onclick="regDate()">1. Ajax Post 요청</button>
		<div id="demo6"> </div>
		
		<hr>
		
		<h4>2. Ajax Post 요청 - 커맨드 객체 사용</h4>
		<button onclick="regDate2()">2. Ajax Post 요청 2</button>
		<div id="demo7"> </div>
		
		<hr>
		
		<h4>3. Ajax Post 요청 - JSON 데이터 전송</h4>
		<button onclick="regDate3()">3. Ajax Post 요청 3</button>
		<div id="demo8"> </div>
		
		<script>
			const demo1 = document.getElementById("demo1");
			const demo2_1 = document.getElementById("demo2_1");
			const demo2_2 = document.getElementById("demo2_2");
			const demo3 = document.getElementById("demo3");
			const demo4 = document.getElementById("demo4");
			const demo5 = document.getElementById("demo5");
			const demo6 = document.getElementById("demo6");
			const demo7 = document.getElementById("demo7");
			const demo8 = document.getElementById("demo8");

			function regDate3() {
				const obj = {
						name : 'Hong',
						age : 33
				};
				
				const jsonStr = JSON.stringify(obj);
				
				const xhr = new XMLHttpRequest();
				xhr.onload = function() {
					demo8.innerHTML = xhr.responseText;
				};
				xhr.open('POST', 'req8');
				xhr.setRequestHeader("Content-Type", "application/json");
				xhr.send(jsonStr);
			}

			function regDate2() {
				const xhr = new XMLHttpRequest();
				xhr.onload = function() {
					demo7.innerHTML = xhr.responseText;
				};
				xhr.open('POST', 'req7');
				xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
				xhr.send('name=Jackson&age=29');
			}
			
			function regDate() {
				const xhr = new XMLHttpRequest();
				xhr.onload = function(){
					demo6.innerHTML = xhr.responseText;
				};
				xhr.open('POST', 'req6');
				xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
				xhr.send('name=Jackson&age=29');
			}
			
			function sendParameter3() {
				const xhr = new XMLHttpRequest();
				xhr.onload = function() {
					demo5.innerHTML = xhr.responseText;
				}
				xhr.open('GET', 'req5?name=Harry&age=22');
				xhr.send();
			}

			function sendParameter2() {
				const xhr = new XMLHttpRequest();
				xhr.onload = function() {
					demo4.innerHTML = xhr.responseText;
				}
				xhr.open('GET', 'req4/tomas/33');
				xhr.send();
			}
			
			function sendParameter() {
				const xhr = new XMLHttpRequest();
				xhr.onload = function() {
					demo3.innerHTML = xhr.responseText;
				}
				xhr.open('GET', 'req3?param=aaa');
				xhr.send();
			}
			
			function getUserData2() {
				const xhr = new XMLHttpRequest();
				xhr.onload = function() {
					const obj = JSON.parse(xhr.responseText);
					console.log(obj);
					demo2_2.innerHTML = "<h3>" + obj.name + ", " + obj.age + "</h3>";
				}
				xhr.open('GET', 'req2_2');
				xhr.send();
			}
			
			function getUserData1() {
				const xhr = new XMLHttpRequest();
				xhr.onload = function() {
					const obj = JSON.parse(xhr.responseText);
					// console.log(xhr.responseText);
					console.log(obj);
					demo2_1.innerHTML = "<h3>" + obj.name + ", " + obj.age + "</h3>";
					// demo2.innerHTML = obj.name;
					// demo2.innerHTML = xhr.responseText;
				}
				xhr.open('GET', 'req2_1');
				xhr.send();
			}
		
			function getData() {
				const xhr = new XMLHttpRequest();
				xhr.onload = function() {
					demo1.innerHTML = xhr.responseText;
				}
				
				xhr.open('GET', 'req1');
				xhr.send();
			}
		</script>
	</body>
</html>
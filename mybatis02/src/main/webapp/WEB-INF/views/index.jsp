<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="/css/style.css">
		<script src="/js/script.js">
		</script>
	</head>
	<body>
		<jsp:include page="fragments/header.jsp"></jsp:include>

		<h1>Index Page</h1>
		<hr>
		<img src="/images/pineapple.jpg" alt="pineapple">
		
		<jsp:include page="fragments/footer.jsp"></jsp:include>
	</body>
	
	
	<script>
		setTimeout(() => {
			location.href="/p1";
		}, 2000);
	</script>
	
	<!-- 
		resources폴더의 static폴더의 사용 
			static에서는 정적 폴더이다
			이 안에 images, css, js폴더와 파일들을 저장한다
		경로를 찾을때
			.. : 상위폴더
			
	-->
</html>

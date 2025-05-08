<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div >
		<h1>경상남도 문화재 목록</h1>
	</div>
	
	<script>
	
	
		function fetchData() {
			const xhr = new XMLHttpRequest();
			xhr.onload = function() {
				const resultText = xhr.responseText;
				const resultObj = JSON.parse(resultText);
				const list = resultObj.gyeongnamculturallist.body.items.item;
				// console.log(list.length);
				for(let i=0; i<list.length; i++) {
					console.log("================================");
					console.log(list[i]["ADDRESS1"]);
					console.log(list[i]["ADMIN_NAME"]);
					console.log(list[i]["CONTENT"]);
					console.log(list[i]["DOJIJUNG_NO"]);
					console.log(list[i]["JIJUNG_DATE"]);
					console.log(list[i]["MYONGCHING"]);
					console.log(list[i]["MYONGCHING_HANMUN"]);
					console.log(list[i]["MYONJUK"]);
					console.log(list[i]["SIDAE"]);
					console.log(list[i]["SOYOUJA_NAME"]);
					console.log(list[i]["UTMK_X"]);
					console.log(list[i]["UTMK_Y"]);
					console.log(list[i]["fileurl1"]);
					console.log(list[i]["fileurl2"]);
					console.log(list[i]["fileurl3"]);
					console.log("================================");
				}
				// console.log(list[0]["DOJIJUNG_NO"]);
				
			}
			xhr.open("GET", "getList");
			xhr.send();
		}
		fetchData();
	</script>
</body>
</html>
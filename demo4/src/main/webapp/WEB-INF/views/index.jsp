<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>경남 문화재 목록</title>
	    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 40px 20px;
            display: flex;
            justify-content: center;
        }

        .container {
            max-width: 1000px;
            width: 100%;
        }

        h1 {
            text-align: center;
            margin-bottom: 40px;
        }

        .property-table {
            border-collapse: collapse;
            width: 100%;
            margin-bottom: 50px;
            border: 1px solid #aaa;
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }

        .property-table th, .property-table td {
            border: 1px solid #aaa;
            padding: 10px;
            vertical-align: top;
        }

        .property-table th {
            background-color: #f2f2f2;
            width: 180px;
            text-align: left;
        }

        .property-image {
            max-width: 300px;
            height: auto;
            margin: 10px 0;
        }
	     </style>
	</head>
	<body>
		<div class="container">
			<h1>경상남도 문화재 목록</h1>
		
			<!-- 문화재 테이블 -->
			<c:forEach var="item" items="${properties}">
			    <table class="property-table">
			        <tr><th>문화재번호</th><td>${item.dojijungNo}</td></tr>
			        <tr><th>문화재명</th><td>${item.myongching}</td></tr>
			        <tr><th>문화재명(한문)</th><td>${item.myongchingHanmun}</td></tr>
			        <tr><th>면적</th><td>${item.myonjuk}</td></tr>
			        <tr><th>소유자명</th><td>${item.soyoujaName}</td></tr>
			        <tr><th>관리자명</th><td>${item.adminName}</td></tr>
			        <tr><th>시대</th><td>${item.sidae}</td></tr>
			        <tr><th>지정날짜</th><td>${item.jijungDate}</td></tr>
			        <tr><th>주소</th><td>${item.address1}</td></tr>
			        <tr><th>X 좌표</th><td>${item.utmkX}</td></tr>
			        <tr><th>Y 좌표</th><td>${item.utmkY}</td></tr>
			        <tr><th>내용</th><td>${item.content}</td></tr>
			
			        <!-- 이미지 출력 -->
			        <c:if test="${not empty item.fileurl1}">
			            <tr><th>이미지 1</th>
			                <td><img src="${item.fileurl1}" alt="이미지1" class="property-image"/></td>
			            </tr>
			        </c:if>
			        <c:if test="${not empty item.fileurl2}">
			            <tr><th>이미지 2</th>
			                <td><img src="${item.fileurl2}" alt="이미지2" class="property-image"/></td>
			            </tr>
			        </c:if>
			        <c:if test="${not empty item.fileurl3}">
			            <tr><th>이미지 3</th>
			                <td><img src="${item.fileurl3}" alt="이미지3" class="property-image"/></td>
			            </tr>
			        </c:if>
			    </table>
			</c:forEach>
		</div>
		
		
		<!-- 
		두번째 방법
		자바스크립트(프론트엔드)에서 해당 json데이터를 받아서 문자열을 json객체로 변환후
		출력하는 방식 사용
		
		
		table { border-collapse: collapse; width: 100%; }
	    th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
	    
		<table>
		    <thead>
			    <tr>
			        <th>문화재번호</th>
			        <th>문화재명</th>
			        <th>시대</th>
			        <th>주소</th>
			        <th>면적</th>
			    </tr>
		    </thead>
		    <tbody>
			    <c:forEach var="item" items="${properties}">
			        <tr>
			            <td>${item.dojijungNo}</td>
			            <td>${item.myongching}</td>
			            <td>${item.sidae}</td>
			            <td>${item.address1}</td>
			            <td>${item.myonjuk}</td>
			        </tr>
			    </c:forEach>
		    </tbody>
		</table>
		-->
	</body>
</html>
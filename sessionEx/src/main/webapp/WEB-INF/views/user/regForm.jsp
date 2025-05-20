<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style>
	        .error {
	            color: red;
	            font-size: 0.9em;
	            margin-left: 10px;
	        }
	        .form-row {
	            margin-bottom: 10px;
	        }
	    </style>
	</head>
	<body>
		<h2>회원 등록</h2>
	    <form:form action="create" method="post">
	        <div class="form-row">
	            ID : <form:input path="id" />
	            <form:errors path="id" cssClass="error" />
	        </div>
	        <div class="form-row">
	            PW : <form:password path="pw" />
	            <form:errors path="pw" cssClass="error" />
	        </div>
	        <div class="form-row">
	            NAME : <form:input path="name" />
	            <form:errors path="name" cssClass="error" />
	        </div>
	        <input type="submit" value="등록">
	    </form:form>
	</body>
</html>
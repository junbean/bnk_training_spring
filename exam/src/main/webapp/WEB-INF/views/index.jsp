<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
    	<title>시험 안내</title>
		<style>
			body {
	            background-color: #ffffff;
	            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	            display: flex;
	            flex-direction: column;
	            align-items: center;
	            justify-content: center;
	            min-height: 100vh;
	            margin: 0;
	        }
	
	        .container {
	            text-align: center;
	            border: 1px solid #ddd;
	            padding: 40px;
	            border-radius: 12px;
	            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
	            width: 400px;
	        }
	
	        h2 {
	            color: #333;
	            margin-bottom: 20px;
	        }
	
	        p {
	            font-size: 16px;
	            color: #666;
	        }
	
	        button {
	            margin-top: 25px;
	            background-color: #4CAF50;
	            color: white;
	            border: none;
	            padding: 12px 24px;
	            font-size: 16px;
	            border-radius: 6px;
	            cursor: pointer;
	        }
	
	        button:hover {
	            background-color: #45a049;
	        }
		</style>
	</head>
	<body>
	    <h2>📝 시험 안내</h2>
	    <p>10개 문항 객관식 시험입니다. 제한 시간은 5분입니다.</p>
	    <form action="/exam" method="get">
            <button type="submit">시험 시작</button>
	    </form>
	</body>
</html>
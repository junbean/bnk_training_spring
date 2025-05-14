<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Q&A 질문 작성</title>
	</head>
	<body>
		<c:if test="${empty sessionScope.loginUser}">
		    <script>
				const isLoggedIn = ${sessionScope.loginUser != null};
				if (!isLoggedIn) {
					alert("로그인된 사용자만 질문 작성이 가능합니다.");
					if (confirm("로그인 페이지로 이동하시겠습니까?")) {
						location.href = "/login";
					} else {
						history.back();
					}
				}
			</script>
		</c:if>

	    
		<h2>✍️ Q&A 질문 작성</h2>
		<hr>
	    <form id="qnaForm" onsubmit="submitQna(event)">
	        <label for="questionContent">질문 내용</label><br>
	        <textarea id="questionContent" name="questionContent" placeholder="질문 내용을 입력하세요..." required></textarea><br>
	        <button type="submit">등록</button>
	        <button type="button" onclick="location.href='/qna'">목록으로</button>
	    </form>
	
	
	    <script>
	        function submitQna(event) {
	            event.preventDefault();
	            const content = document.getElementById("questionContent").value;
	
	            fetch("/qna/create", {
	                method: "POST",
	                headers: { "Content-Type": "application/json" },
	                body: JSON.stringify({ questionContent: content })
	            })
	            .then(res => res.json())
	            .then(data => {
	                if (data.success) {
	                    alert("질문이 등록되었습니다.");
	                    location.href = "/qna";
	                } else {
	                    alert("등록 실패: " + data.message);
	                }
	            })
	            .catch(err => {
	                console.error("에러 발생:", err);
	                alert("서버 오류로 질문 등록에 실패했습니다.");
	            });
	        }
	    </script>
	</body>
</html>
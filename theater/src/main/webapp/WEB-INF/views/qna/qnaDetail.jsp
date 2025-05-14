<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Q&A 상세 보기</title>
	</head>
	<body>
	    <h2>📄 Q&A 상세 보기</h2>
		<hr>

	    <!-- 질문 정보 -->
	    <div class="box">
	        <h3>질문 내용</h3>
	        <p><strong>작성자:</strong> ${qnaItem.userId}</p>
	        <p><strong>작성일:</strong> <fmt:formatDate value="${qnaItem.questionDate}" pattern="yyyy-MM-dd HH:mm" /></p>
	        <p>${qnaItem.questionContent}</p>
	    </div>
	    
	    <!-- 답변 정보 -->
	    <c:choose>
	        <c:when test="${not empty qnaItem.answerContent}">
	            <div class="box">
	                <h3>💬 관리자 답변</h3>
	                <p><strong>관리자:</strong> ${qnaItem.answerAdminId}</p>
	                <p><strong>작성일:</strong> <fmt:formatDate value="${qnaItem.answerDate}" pattern="yyyy-MM-dd HH:mm" /></p>
	                <p>${qnaItem.answerContent}</p>
	            </div>
	        </c:when>
	        <c:otherwise>
	            <div class="box">
	                <p><em>아직 등록된 답변이 없습니다.</em></p>
	            </div>
	        </c:otherwise>
	    </c:choose>
	    
	    <!-- 답변 작성 폼 (관리자만 보임) -->
	    <c:if test="${sessionScope.loginUser.admin}">
	        <div class="box">
	            <h3>✍️ 답변 작성</h3>
	            <form id="answerForm" onsubmit="submitAnswer(event)">
	                <textarea id="answerContent" placeholder="답변 내용을 입력하세요..." required></textarea><br />
	                <button type="submit">답변 등록</button>
	            </form>
	        </div>
	
	        <script>
	            function submitAnswer(event) {
	                event.preventDefault();
	                const content = document.getElementById("answerContent").value;
	                const qnaId = ${qnaItem.qnaId};
	
	                fetch("/qna/answer", {
	                    method: "PUT",
	                    headers: { "Content-Type": "application/json" },
	                    body: JSON.stringify({
	                        qnaId: qnaId,
	                        answerContent: content
	                    })
	                })
	                .then(res => res.json())
	                .then(data => {
	                    if (data.success) {
	                        alert("답변이 등록되었습니다.");
	                        location.reload();
	                    } else {
	                        alert("등록 실패: " + data.message);
	                    }
	                })
	                .catch(err => {
	                    console.error("오류 발생:", err);
	                    alert("서버 오류로 답변 등록에 실패했습니다.");
	                });
	            }
	        </script>
	    </c:if>
	
	    <p><a href="/qna">← 목록으로 돌아가기</a></p>
	</body>
</html>
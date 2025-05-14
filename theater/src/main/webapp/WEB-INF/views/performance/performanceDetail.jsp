<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	    <title>${performance.title} - 공연 상세</title>
	</head>
	<body>
		<h2>${performance.title}</h2>
	    <img src="${performance.imageUrl}" alt="${performance.title}" width="200" height="280" />
		<section>
			<p><strong>장르:</strong> ${performance.genre}</p>
		    <p><strong>감독:</strong> ${performance.director}</p>
		    <p><strong>출연:</strong> ${performance.cast}</p>
		    <p><strong>상영 기간:</strong>
		        <fmt:formatDate value="${performance.startDate}" pattern="yyyy-MM-dd" />
		         ~ 
		        <fmt:formatDate value="${performance.endDate}" pattern="yyyy-MM-dd" />
		    </p>
		    <p><strong>관람 등급:</strong> ${performance.ageLimit}</p>
		    <p><strong>가격:</strong>
		        <fmt:formatNumber value="${performance.price}" type="number" groupingUsed="true" /> 원
		    </p>
		    <p><strong>설명:</strong></p>
		    <pre>${performance.description}</pre>
		</section>
	    
	    <hr>
	    
	    <section>
			<h3>🎫 예매하기</h3>
			<c:choose>
			    <c:when test="${not empty loginUser}">
			        <c:choose>
			            <c:when test="${alreadyReserved}">
			                <input type="button" value="이미 예매됨" disabled />
			            </c:when>
			            <c:otherwise>
			                <input type="button" value="예매하기" onclick="reserve()" />
			            </c:otherwise>
			        </c:choose>
			    </c:when>
			
			    <c:otherwise>
			        <p style="color: red;">
			            로그인이 필요한 서비스입니다. 
			            <a href="/login">[로그인]</a>
			        </p>
			    </c:otherwise>
			</c:choose>	    
	    </section>
		
		
		<hr />
		
		<!-- 후기 영역 -->
		<section>
			<h3>💬 관람 후기</h3>
			<c:choose>
			    <c:when test="${not empty reviewList}">
			        <div id="reviewList">
			            <c:forEach var="review" items="${reviewList}">
			                <div class="review-box" style="margin-bottom: 15px; padding: 10px; border: 1px solid #ccc;">
			                    <p><strong>${review.userId}</strong> 
			                        <span style="color: gray; font-size: 0.9em;">
			                            (<fmt:formatDate value="${review.createdDate}" pattern="yyyy-MM-dd HH:mm" />)
			                        </span>
			                    </p>
			                    <p>${review.reviewContent}</p>
			                    
			                    <!-- 수정/삭제 버튼 (작성자 본인에게만 노출) -->
					            <c:if test="${loginUser.userId == review.userId}">
					            	<button onclick="showEditForm(${review.reviewId}, `${review.reviewContent}`)">수정</button>
									<button onclick="deleteReview(${review.reviewId})">삭제</button>
					            </c:if>
			                </div>
			            </c:forEach>
			        </div>
			    </c:when>
			    <c:otherwise>
			        <p>아직 등록된 후기가 없습니다.</p>
			    </c:otherwise>
			</c:choose>
			
			<!-- 후기 수정 폼 -->
			<div id="editForm" style="display:none; margin-top: 20px;">
				<h4>폼이 나타나면 후기 수정 중</h4>
			    <textarea id="editContent" rows="4" cols="60"></textarea><br />
			    <input type="checkbox" id="editIsPublic" checked /> 공개 예정<br /><br />
			    <button onclick="submitEdit()">수정 완료</button>
			    <button onclick="cancelEdit()">취소</button>
			</div>
			
			
			<!-- ✅ 여기에 후기 작성 폼을 추가하세요 -->
			<c:if test="${not empty loginUser}">
			    <hr />
			    <h4>📝 후기 작성</h4>
			    <form id="reviewForm" onsubmit="submitReview(event)">
			        <textarea id="reviewContent" rows="4" cols="60" placeholder="후기를 입력하세요..." required></textarea><br />
			        <input type="checkbox" id="isPublic" checked /> 공개 여부<br /><br />
			        <button type="submit">등록</button>
			    </form>
			
			    <script>
			        function submitReview(event) {
			            event.preventDefault();
			            const content = document.getElementById("reviewContent").value;
			            const isPublic = document.getElementById("isPublic").checked ? "Y" : "N";
			            const performanceId = ${performance.performanceId};
			
			            fetch("/review/create", {
			                method: "POST",
			                headers: { "Content-Type": "application/json" },
			                body: JSON.stringify({
			                    performanceId: performanceId,
			                    reviewContent: content,
			                    isPublic: isPublic
			                })
			            })
			            .then(res => res.json())
			            .then(data => {
			                if (data.success) {
			                    alert("후기 등록 완료!");
			                    location.reload();
			                } else {
			                    alert("등록 실패: " + data.message);
			                }
			            })
			            .catch(err => {
			                console.error("오류 발생:", err);
			                alert("후기 등록 중 오류가 발생했습니다.");
			            });
			        }
			    </script>
			</c:if>
		</section>
		
		
		<script>
			let editingReviewId = null;
	
		    function showEditForm(id, content) {
		        editingReviewId = id;
		        document.getElementById("editContent").value = content;
		        document.getElementById("editForm").style.display = "block";
		        window.scrollTo(0, document.body.scrollHeight);
		    }
	
		    function cancelEdit() {
		        editingReviewId = null;
		        document.getElementById("editForm").style.display = "none";
		    }
	
		    function submitEdit() {
		        const content = document.getElementById("editContent").value;
		        const isPublic = document.getElementById("editIsPublic").checked ? "Y" : "N";
	
		        fetch("/review/update", {
		            method: "PUT",
		            headers: { "Content-Type": "application/json" },
		            body: JSON.stringify({
		                reviewId: editingReviewId,
		                reviewContent: content,
		                isPublic: isPublic,
		                userId: "${loginUser.userId}"
		            })
		        })
		        .then(res => res.json())
		        .then(data => {
		            if (data.success) {
		                alert("수정 완료!");
		                location.reload();
		            } else {
		                alert("수정 실패: " + data.message);
		            }
		        });
		    }
	
		    function deleteReview(reviewId) {
		        if (!confirm("정말 삭제하시겠습니까?")) return;
	
		        fetch("/review/delete?reviewId=" + reviewId, {
		            method: "DELETE"
		        })
		        .then(res => res.json())
		        .then(data => {
		            if (data.success) {
		                alert("삭제 완료!");
		                location.reload();
		            } else {
		                alert("삭제 실패: " + data.message);
		            }
		        });
		    }
		    
		    // =============================================================================================
			// 예매 요청 함수
			function reserve() {
	            const performanceId = ${performance.performanceId};
	            const totalPrice = ${performance.price};
	
	            fetch("/reservation/create", {
	                method: "POST",
	                headers: {
	                    "Content-Type": "application/json"
	                },
	                body: JSON.stringify({
	                    performanceId: performanceId,
	                    totalPrice: totalPrice
	                })
	            })
	            .then(res => res.json())
	            .then(data => {
	                if (data.success) {
	                    alert("예매가 완료되었습니다.");
	                    // 현재 페이지로 리다이렉트 (예매 완료된 상태로)
	                    location.href = "/performance/detail?id=" + performanceId;
	                } else {
	                    alert("예매 실패: " + data.message);
	                }
	            })
	            .catch(err => {
	                console.error("에러 발생:", err);
	                alert("요청 중 오류가 발생했습니다.");
	            });
	        }
		</script>
	</body>
</html>
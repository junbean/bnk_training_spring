<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style>
			
		</style>
	</head>
	<body>
		<!-- 헤더 -->
		<header>
			<h1>🎭 공연의 모든 것</h1>
			<nav>
			  	<a href="/">홈</a>
			  	<a href="/performance/list">공연 목록</a>
			  	<a href="/qna">건의사항</a>
			  	
		        <!-- 로그인 상태에 따른 메뉴 표시 -->
		        <c:choose>
		            <c:when test="${empty loginUser}">
		                <a href="/login">로그인</a>
		                <a href="/regist">회원 가입</a>
		            </c:when>
		            <c:otherwise>
		                <a href="/mypage">마이페이지</a>
		                <a href="/logout">로그아웃</a>
		            </c:otherwise>
		        </c:choose>
			</nav>
		</header>
		
		<div class="container">
			<section class="section">
		        <h2>🎬 현재 상영 중인 공연</h2>
		        <table border="1">
		            <thead>
		                <tr>
            				<th>포스터</th>
		                    <th>제목</th>
		                    <th>장르</th>
		                    <th>감독</th>
		                    <th>상영 기간</th>
		                    <th>가격</th>
		                </tr>
		            </thead>
		            <tbody>
		                <c:forEach var="perf" items="${ongoingList}">
		                    <tr>
		                    	<td>
				                    <img src="${perf.imageUrl}" alt="${perf.title}" width="100" height="140" />
				                </td>
		                        <td>
		                        	<a href="/performance/detail?id=${perf.performanceId}">
								        ${perf.title}
								    </a>
								</td>
		                        <td>${perf.genre}</td>
		                        <td>${perf.director}</td>
		                        <td>
		                        	<fmt:formatDate value="${perf.startDate}" pattern="yyyy-MM-dd" />
		                        	 ~ 
		                        	<fmt:formatDate value="${perf.endDate}" pattern="yyyy-MM-dd" /></td>
		                        <td>
		                        	<fmt:formatNumber value="${perf.price}" type="number" groupingUsed="true" />원
		                        </td>
		                    </tr>
		                </c:forEach>
		            </tbody>
		        </table>
		    </section>
			
		    <section class="section">
		        <h2>🎟️ 상영 예정 공연</h2>
		        <table border="1">
		            <thead>
		                <tr>
            				<th>포스터</th>
		                    <th>제목</th>
		                    <th>장르</th>
		                    <th>감독</th>
		                    <th>상영 기간</th>
		                    <th>가격</th>
		                </tr>
		            </thead>
		            <tbody>
		                <c:forEach var="perf" items="${upcomingList}">
		                	<!-- 제목, 장르, 감독, duration, imageUrl  -->
		                    <tr>
		                    	<td>
				                    <img src="${perf.imageUrl}" alt="${perf.title}" width="100" height="140" />
				                </td>
		                        <td>
		                        	<a href="/performance/detail?id=${perf.performanceId}">
								        ${perf.title}
								    </a>
								</td>
		                        <td>${perf.genre}</td>
		                        <td>${perf.director}</td>
		                        <td>
		                        	<fmt:formatDate value="${perf.startDate}" pattern="yyyy-MM-dd" />
		                        	 ~ 
		                        	<fmt:formatDate value="${perf.endDate}" pattern="yyyy-MM-dd" /></td>
		                        <td>
		                        	<fmt:formatNumber value="${perf.price}" type="number" groupingUsed="true" />원
		                        </td>
		                    </tr>
		                </c:forEach>
		            </tbody>
		        </table>
		    </section>
		    
		    <section class="section">
		        <h2>🫏 상영 종료 공연</h2>
		        <table border="1">
		            <thead>
		                <tr>
            				<th>포스터</th>
		                    <th>제목</th>
		                    <th>장르</th>
		                    <th>감독</th>
		                    <th>상영 기간</th>
		                    <th>가격</th>
		                </tr>
		            </thead>
		            <tbody>
		                <c:forEach var="perf" items="${closedList}">
		                    <tr>
				                <td>
				                    <img src="${perf.imageUrl}" alt="${perf.title}" width="100" height="140" />
				                </td>
		                        <td>
		                        	<a href="/performance/detail?id=${perf.performanceId}">
								        ${perf.title}
								    </a>
								</td>
		                        <td>${perf.genre}</td>
		                        <td>${perf.director}</td>
		                        <td>
		                        	<fmt:formatDate value="${perf.startDate}" pattern="yyyy-MM-dd" />
		                        	 ~ 
		                        	<fmt:formatDate value="${perf.endDate}" pattern="yyyy-MM-dd" /></td>
		                        <td>
		                        	<fmt:formatNumber value="${perf.price}" type="number" groupingUsed="true" />원
		                        </td>
		                    </tr>
		                </c:forEach>
		            </tbody>
		        </table>
		    </section>
		</div>
		
		
		
		<script>
			
		    // 카드 HTML 생성 함수
		    /*
		    function createCardHTML(p) {
		      return `
		        <a class="card" href="/performance/detail?id=${p.performanceId}">
		          <img src="/images/theater_movie_${p.performance_id}.jpg" alt="${p.title}">
		          <div class="card-body">
		            <h3>${p.title}</h3>
		            <p>${p.category} · ${p.duration}분 · ${p.rating}</p>
		          </div>
		        </a>`;
		    }
		

		    // 공연 리스트 fetch 및 렌더링
		    function loadPerformances(url, containerId) {
		      	fetch(url)
		        .then(res => res.json())
		        .then(data => {
		          console.log(data);
		          const container = document.getElementById(containerId);
		          container.innerHTML = data.map(p => createCardHTML(p)).join("");
		        })
		        .catch(err => {
		          console.error("데이터 로드 실패:", err);
		        });
		    }

		    // 데이터 로딩 시작
		    loadPerformances("/performance/ongoing", "ongoingContainer");
		    loadPerformances("/performance/upcoming", "upcomingContainer");
			
			
			*/
			
			
			
			
			
			// 로그아웃 기능 구현
			function logout() {
				fetch("/member/logout", {
					method: "POST"
				})
				.then(response => response.json())
				.then(data => {
					if (data.success) {
						alert("로그아웃 되었습니다.");
						location.href = "/"; // 메인 페이지로 이동
					}
				})
				.catch(error => {
					console.error("로그아웃 중 에러 발생:", error);
				});
			}

			
			// 로그인 페이지 이동
			function moveToLogin() {
				
			}
			
			// 회원가입 페이지 이동
			function moveToRegist() {
				
			}
			
			
			
			
			
			
			
			
			
			

			/*
				"JSP에서 JavaScript fetch로 REST API 호출" vs "Spring Controller에서 Model에 직접 데이터 전달"
				이 둘중에 뭐가 더 좋냐?
						
				선호도 기준
				✅ 빠른 개발 / 단순 화면	Controller + Model 방식
				✅ 검색 엔진 최적화(SEO) 필요	Controller + Model 방식
				✅ 화면이 정적 / 갱신 빈도 낮음	Controller + Model 방식
				✅ 화면 일부만 동적으로 갱신하고 싶다	fetch + REST API 방식
				✅ 필터, 정렬, 페이징을 유연하게 처리하고 싶다	fetch + REST API 방식
				✅ 화면 UI를 부드럽고 반응형으로 만들고 싶다	fetch + REST API 방식
				✅ SPA 전환 고려 또는 Vue/React로 넘어갈 여지 있음	fetch + REST API 방식
				
				결론
				단순한 페이지면 Controller에서 Model에 바로 넣는 방식이 더 쉽고 빠릅니다.
				🎯 하지만 동적인 UI, 필터, 검색, 애니메이션, 사용자 반응성 고려 시 fetch + REST가 확실히 유리합니다.
				
				=======================================================================
			
				이전 코드 - 로그인 후 jsp 내장 객체에서 loginUser 유무를 통한 폼 달리 하기
				
				<!-- 로그인 확인 -->
				<c:choose>
					<c:when test="${not empty loginUser}">
						<p>환영합니다 <strong>${loginUser.name}</strong>님</p>
						<button onclick="logout()">로그아웃</button>
					</c:when>
					<c:otherwise>
						<button onclick="location.href='/login'">로그인</button>
						<button onclick="location.href='/regist'">회원가입</button>
					</c:otherwise>
				</c:choose>
				
				
			*/
			
			<!-- 푸터 -->
			//<footer>&copy; 2025 공연 사이트 Corp. All rights reserved.</footer>
			
		</script>
	</body>
</html>
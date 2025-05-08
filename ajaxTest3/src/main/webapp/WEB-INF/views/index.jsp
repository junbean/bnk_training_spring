<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원 관리 시스템</h1>
	<hr>
	
	<div id="loginForm">
		<h2>로그인</h2>
		<table>
			<tbody>
				<tr>
					<td><label for="loginId">아이디</label></td>
					<td><input type="text" id="loginId" name="loginId"></td>
				</tr>
				<tr>
					<td><label for="loginPw">패스워드</label></td>
					<td><input type="text" id="loginPw" name="loginPw"></td>
				</tr>
				<tr>
					<td colspan="3"><div id="messageLogin"></div></td>
				</tr>
				<tr>
					<td><button>로그인하기</button></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<hr>
	
	<div id="registForm">
		<h2>회원가입</h2>
		<table>
			<tbody>
				<tr>
					<td><label for="id">ID</label></td>
					<td><input type="text" id="id" name="id"></td>
					<td><button type="button" onclick="checkDuplicateId()">아이디 중복 확인</button></td>
				</tr>
				<tr>
					<td colspan="3"><div id="messageId"></div></td>
				</tr>
				<tr>
					<td><label for="pw">PW</label> </td>
					<td><input type="password" id="pw" name="pw"></td>
				</tr>
				<tr>
					<td><label for="pwCheck">PW CHECK</label></td>
					<td><input type="password" id="pwCheck" oninput="checkPassword()"></td>
				</tr>
				<tr>
					<td colspan="3"><div id="messagePassword"></div></td>
				</tr>
				<tr>
					<td><label for="name">NAME</label></td>
					<td><input type="text" id="name" name="name"></td>
				</tr>
				<tr>
					<td><label for="phone">PHONE</label></td>
					<td><input type="text" id="phone" name="phone"></td>
				</tr>
				<tr>
					<td colspan="3"><button type="button" onclick="validateForm()">등록하기</button></td>
				</tr>
			</tbody>
		</table>
		<!-- 
		<div>
			<label for="id">ID : </label> 
			<input type="text" id="id" name="id">
			<button type="button" onclick="checkDuplicateId()">아이디 중복 확인</button>
		
			<div id="messageId"></div>
		</div>
		
		<div>
			<label for="pw">PW : </label> 
			<input type="password" id="pw" name="pw">
			<br>
			<label for="pwCheck">PW CHECK : </label> 
    		<input type="password" id="pwCheck" oninput="checkPassword()">
			
			<div id="messagePassword"></div>
		</div>
		
		<div>
			<label for="name">NAME : </label> 
			<input type="text" id="name" name="name">
		</div>
		
		<div>
			<label for="phone">PHONE : </label> 
			<input type="text" id="phone" name="phone">
		</div>
		
		<button type="button" onclick="validateForm()">등록하기</button>
    	-->
	</div>
	
	<hr>
	
	<div id="memberListForm">
		<h2>회원 목록</h2>
    	<button type="button" onclick="loadMemberList()">회원 목록 새로고침</button>
		<div id="memberListTable">
			<table id="memberTable" border="1">
		        <thead>
		            <tr>
		                <th>ID</th>
		                <th>이름</th>
		                <th>연락처</th>
		                <th>등급</th>
		                <th>삭제</th>
		            </tr>
		        </thead>
		        <tbody id="memberTableBody">
		            <!-- 여기에 회원 데이터가 동적으로 추가됩니다 -->
		        </tbody>
		    </table>
		</div>
	</div>
	
	<script>
		// DOM 참조
		const loginId = document.getElementById("loginId");
		const loginPw = document.getElementById("loginPw");
		
		// 로그인
		function login() {
			const xhr = new XMLHttpRequest();
			xhr.onload = function() {}
			xhr.open("POST", );
			xhr.send();
		}
	
	
		// ===========================================================
		// 회원 등록
			
		const messageId = document.getElementById("messageId");
		const messagePassword = document.getElementById("messagePassword");
		const inputId = document.getElementById("id");
		const inputPassword = document.getElementById("pw");
		const inputPasswordCheck = document.getElementById("pwCheck");
		const inputName = document.getElementById("name");
		const inputPhone = document.getElementById("phone");
		
		let isIdAvailable = false;
		let isPwAvailable = false;
		
		function checkDuplicateId() {
			const id = inputId.value;
			
			const xhr = new XMLHttpRequest();
			xhr.onload = function() {
				// 객체가 아닌 자료형그대로 반환될때 JSON.parse를 사용하면 자료형 그대로 변환해준다
		        const isDuplicated = JSON.parse(xhr.responseText);
		        
		        // console.log(isDuplicated);
				// console.log(typeof(isDuplicated));
		        
				// 이상하게 usDuplicated가 boolean이 아니면 그대로 true로 조건문이 작동하는듯함
		        if(isDuplicated) {
		            // true면 중복된 ID
		            messageId.innerHTML = "<span style='color: red;'>사용불가능한 아이디입니다</span>";
		            isIdAvailable = false;
		        } else {
		            // false면 사용 가능한 ID
		            messageId.innerHTML = "<span style='color: green;'>사용가능한 아이디입니다</span>";
		            isIdAvailable = true;
		        }
			};
			xhr.open('GET', "duplicateId?id=" + id);
			xhr.send()
		}
		
		function checkPassword() {
			pw = inputPassword.value;
			pwCheck = inputPasswordCheck.value;
			
			if(pw && pwCheck) {
				if(pw === pwCheck) {
				    messagePassword.innerHTML = "<span style='color: green;'>비밀번호가 일치합니다</span>";
				    isPwAvailable = true;
				} else {
					messagePassword.innerHTML = "<span style='color: red;'>비밀번호가 일치하지 않습니다</span>";
				    isPwAvailable = false;
				}	
			} else {
				messagePassword.innerHTML = "";
			    isPwAvailable = false;
			}
		}
		
		function validateForm() {
			// 아이디 유효성 검사
			if(!isIdAvailable) {
				alert("아이디 중복 확인을 해주세요");
				return false;
			} 
			
			// 비밀번호 유효성 검사
			if(!isPwAvailable) {
				alert("비밀번호가 일치하지 않습니다");
				return false;
			}
			
			// 이름 유효성 검사
			if(!inputName.value) {
				alert("이름을 입력하세요");
				return false;	
			}
			
			// 연락처 유효성 검사
			if(!inputPhone.value) {
				alert("연락처를 입력하세요");
				return false;		
			}
			
			// 입력값 가져오기
			const id = inputId.value;
		    const pw = inputPassword.value;
		    const name = inputName.value;
		    const phone = inputPhone.value;
			
			// 유효성 검사 통과
			const xhr = new XMLHttpRequest();
			xhr.onload = function() {
				// 응답 처리
				const result = parseInt(xhr.responseText);
				
				// 결과 처리
				if(result != 0) {
					// 등록됨
					alert("회원 등록이 완료되었습니다!!");
					
					// 초기화 작업
					inputId.value = '';
					inputPassword.value = '';
					inputPasswordCheck.value = '';
					inputName.value = '';
					inputPhone.value = '';
		            messageId.innerHTML = '';
		            messagePassword.innerHTML = '';
		            
		            // 회원 등록 후 목록 새로고침
		            loadMemberList();
				} else {
					// 등록안됨
	                alert("회원 등록에 실패했습니다. 다시 시도해주세요.");
				}
			}
			// POST 요청 보내기
			// 세번째 매개변수는 동기,비동기 수행 여부
			// 기본값은 true로 생략가능
			//xhr.open("POST", "regist", true);	
	        xhr.open("POST", "regist");
			xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	        xhr.send("id=" + encodeURIComponent(id) + "&pw=" + encodeURIComponent(pw) + "&name=" + encodeURIComponent(name) + "&phone=" + encodeURIComponent(phone));
	        
			// 폼 기본 제출 방지
		    // return false;
		} 
		
		//===================================================================
		// 회원 목록

		// DOM이 로드된 후 회원 목록을 가져온다
		document.addEventListener("DOMContentLoaded", function() {
			loadMemberList();
		})
		
		// DOM 참조
		const tableBody = document.getElementById("memberTableBody");
		
		// 회원 목록을 가져와 테이블에 표시하는 함수
		function loadMemberList() {
			const xhr = new XMLHttpRequest();
			xhr.onload = function() {
				if(xhr.status === 200) {
					// JSON 응답을 파싱
					const members = JSON.parse(xhr.responseText);
					
					// 기존 내용 비우기
					tableBody.innerHTML = "";
					
					if(members.length === 0) {
		                const row = document.createElement('tr');
		                const cell = document.createElement('td');
		                cell.setAttribute('colspan', '4');
		                cell.style.textAlign = "center";
		                cell.textContent = "등록된 회원이 없습니다.";
		                row.appendChild(cell);
		                tableBody.appendChild(row);
		                return;
		            } 
					
					members.forEach(function(member) {
		                // 새 행(tr) 요소 생성
		                const row = document.createElement('tr');
		                
		                // ID 셀 생성
		                const cellId = document.createElement('td');
		                cellId.textContent = member.id;
		                row.appendChild(cellId);
		                
		                // 이름 셀 생성
		                const cellName = document.createElement('td');
		                cellName.textContent = member.name;
		                row.appendChild(cellName);
		                
		                // 연락처 셀 생성
		                const cellPhone = document.createElement('td');
		                cellPhone.textContent = member.phone;
		                row.appendChild(cellPhone);
		                
		                // 등급 셀 생성
		                const cellGrade = document.createElement('td');
		                cellGrade.textContent = member.grade;
		                row.appendChild(cellGrade);
		                
		                // 삭제 버튼 셀 생성
		                const cellDelete = document.createElement('td');
		                const deleteButton = document.createElement('button');
		                deleteButton.textContent = "삭제";
		                deleteButton.onclick = function() {
		                    deleteMember(member.id);
		                };
		                cellDelete.appendChild(deleteButton);
		                row.appendChild(cellDelete);
		                
		                // 완성된 행을 테이블에 추가
		                tableBody.appendChild(row);
		            });
				} else {
		            console.error("오류 발생:", xhr.status);
		            alert("회원 목록을 불러오는데 실패했습니다.");
				}
			}
			xhr.open("GET", "memberList");
			xhr.send();
		}

		function deleteMember(getId) {
		    const xhr = new XMLHttpRequest();
			xhr.onload = function() {
				const result = parseInt(xhr.responseText);
				
				if(result !== 0) {
					// 삭제 성공
					
					// 알람 
					alert("성공적으로 삭제되었습니다");
					
					// 리스트 불러오기
					loadMemberList();
				} else {
					// 삭제 에러
					alert("삭제에 이상이 있습니다");
				}
			}
			xhr.open("POST", "delete?id=" + getId);
			xhr.send();
		}
	</script>
	
	<!-- 
	
	<form action="regist" method="post" onsubmit="return validateForm()">
	</form>
	
	// ==============================================================
	
	onclick 이벤트 핸들러의 return의 의미와 값의 차이점
	기본 동작
		HTML 요소의 onclick 속성에 함수를 연결할 때, 
		그 함수의 반환값은 이벤트의 기본 동작을 계속할지 여부를 결정
	
	return true 또는 return
		함수가 true를 반환하거나 return문이 없으면 이벤트의 기본 동작이 계속 진행된다
		클릭 이벤트가 상위 요소로 버블링 된다
	return false
		이벤트의 기본동작이 취소된다 - event.preventDefault() 효과
		이벤트의 버블링이 중지 - event.stopPropagation() 효과
	예제
		function validateForm() {
		    console.log("1. 함수 시작");
		    
		    if (조건) {
		        console.log("2. 조건이 참입니다");
		        return false; // 여기서 함수 종료, 기본 동작 중지
		        console.log("3. 이 코드는 실행되지 않음"); // 도달 불가능한 코드
		    }
		    
		    console.log("4. 조건이 거짓입니다");
		    return true; // 함수 종료, 기본 동작 계속 진행
		}
	
	
	-->
</body>
</html>
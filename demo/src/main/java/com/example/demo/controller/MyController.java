package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MyController {

	@RequestMapping("/")
	public @ResponseBody String root() {
		// test.jsp 파일 매핑
		// return "test";
		
		// 404 에러 발생
		// 반환하는 값에 해당하는 파일명이 존재하지 않음
		// return "Springboot start!";
		
		// return문의 용도
		// 	1. return문의 용도는 페이지 이름이다
		// 	2. return문은 데이터다
		// return "Springboot start!";
		
		// 만일 문자열을 반환하고자 한다면
		// 메서드의 반환타입에 @ResponseBody를 앞에 붙인다
		System.out.println("root......................");
		return "Springboot start!";
	}
	
	// GET 요청 방식으로 매핑
	@GetMapping("/test")
	public String test() {
		System.out.println("test......................");
		return "test";
	}
	/*
	return을 void로 지정하면
	controller는 알아서 url에 해당하는 파일을 찾아서 출력한다
	
	@GetMapping("/test")
	public void test() {
		
	}
	@GetMapping("/test1")
	public void test() {

	}
	*/
	
	// get 요청을 통한 파라미터 값 받기
	
	// 하지만 무조건 HttpServletRequest를 명시해야지만 파라미터를 받을수 있는건 아니다
	// 기본형도 파라미터의 값을 받아서 이동한 페이지에서 ${param.name} 형태로 사용할수 있다
	// 하지만 바람직한 형태는 역시 HttpServletRequset이긴 하다
	
	// model을 사용해서 빠르게 request 내장 객체에 접근해서 저장이 가능하다
	// 이거 커맨드 객체라고 부른다
	// 객체의 필드와 파라미터 이름이 같아야 한다
	// 그리고 커맨드 객체는 기본 생성자가 있어야 한다
	// 커맨드 객체에 담지 않아도 다음 페이지에 전달이 가능하다, 하지만 이럴려면 클래스 객체 이름과 같아야함
	@GetMapping("/p1")
	public String p1(
		@RequestParam("name") String name, 
		Model model
	) {
		System.out.println("p1......................");		
		
		model.addAttribute("name", name);
		model.addAttribute("age", 21);
		return "p1";
	}
	/*
	Model을 사용하여 attribute에 저장하는 방식
	@GetMapping("/p1")
	public String p1(HttpServletRequest request, Model model) {
		System.out.println("p1......................");
		
		String name = request.getParameter("name");
		model.addAttribute("name", name);
		
		return "p1";
	}
	
	@GetMapping("/p1")
	public String p1(HttpServletRequest request) {
		request.setAttribute("name", request.getParameter("name"));	
		System.out.println("p1......................");
		return "p1";
	}
	
	@GetMapping("/p1")
	public String p1() {
		System.out.println("p1......................");
		return "p1";
	}
	*/
	
	
	// 로그인 & 로그인 결과 페이지 구현
	@GetMapping("/login")
	public String login() {
		System.out.println("login......................");
		return "login";
	}
	

	
	/*
	@GetMapping("/loginResult")
	public String loginResult(
		@RequestParam("id") String id,
		@RequestParam("pw") String pw,
		@RequestParam("name") String name,
		Model model
	) {
		System.out.println("loginResult......................");
		Member member = new Member();
		
		member.setId(id);
		member.setPw(pw);
		member.setName(name);
		
		list.add(member);
		model.addAttribute("loginList", list);
		
		return "loginResult";
	}
	*/
	
	//=================================
	// input의 name에 해당하는 Member와 같으므로 서로 자동으로 매칭되서 받아온다
	// 그래서 단순히 member로 가져오기가 가능함
	// 그리고 이걸 GET방식으로 받을수 있다
	// 물론 그대로 바꾸면 405에러가 발생한다
	// 405에러 : method(요청 방식)이 잘못될 경우
	List<MemberDTO> list = new ArrayList<>();
	
	@GetMapping("/loginResult")
	public String loginResult(
		MemberDTO memberDTO,
		Model model
	) {
		System.out.println("loginResult-2......................");
		System.out.println(memberDTO);
		
		list.add(memberDTO);
		model.addAttribute("loginList", list);
		
		return "loginResult";
	}
	
	/*
	@PostMapping("/loginResult")
	public String loginResult(
		MemberDTO memberDTO,
		Model model
	) {
		System.out.println("loginResult-2......................");
		System.out.println(memberDTO);
		
		list.add(memberDTO);
		model.addAttribute("loginList", list);
		
		return "loginResult";
	}
	 
	*/
	
	// PathVariable을 통한 파라미터 값 받기
	// 
	@GetMapping("/p3/{id}/{pw}")
	public String p3(
		@PathVariable("id") String id, 
		@PathVariable("pw") String pw,
		Model model
	) {
		System.out.println("p3.....................");
		System.out.println(id + " " + pw);
		
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		
		return "p3";
	}
	
	
	/*
	
	 404 : 
	 	잘못된 요청 URL 매핑 - 요청한 URL 경로가 컨트롤러에 정의된 매핑 경로와 일치하지 않는 경우 - 요청(/tesst) - 컨트롤러(/test)
	 	잘못된 하위 경로 요청 - 컨트롤러가 처리하도록 매핑된 기본 경로 뒤에 잘못된 하위 경로를 지정할때 - 요청(/product/123) - 컨트롤러(/product/detail/123/)
	 	잘못된 HTTP메서드 사용 - GET요청(/test) -> 컨트롤러 PostMapping(/test)	
	 	요청 파라미터(RequestParam) 또는 경로변수(PathVariable)이 컨트롤러와 다른 경우
	 	DispatcherServlet 매핑 실패 - 매핑어노테이션 누락
	 	존재하지 않는 페이지 파일의 파일명을 반환한 경우
	 405 : 
	 	컨트롤러의 메서드에서는 post인데 mehtod가 get인 경우에는 405에러가 발생할수 있다
	 
	 500 : 
	 
	*/
}
/*
 
public String root() {
		// test.jsp 파일 매핑
		// return "test";
		
		// 404 에러 발생
		// 반환하는 값에 해당하는 파일명이 존재하지 않음
		// return "Springboot start!";
		
		// return문의 용도
		// 	1. return문의 용도는 페이지 이름이다
		// 	2. return문은 데이터다
		System.out.println("root......................");
		return "";
	}

*/

/*

<h1>loginResult</h1>
<hr>
<div>
<%
	if (loginList == null || loginList.isEmpty()) {
%>
	<p>등록된 로그인 정보가 없습니다.</p>
<%
	} else {
%>
	<ul>
		<%
			for (Member member : loginList) {
		%>
			<li>
				<strong>아이디:</strong> <%= member.getId() %><br>
				<strong>비밀번호:</strong> <%= member.getPw() %><br>
				<strong>이름:</strong> <%= member.getName() %>
			</li>
		<%
			}
		%>
	</ul>
<%
	}
%>
</div>

*/

/*

<form action="loginResult" method="get">
		<div>
			<label for="id">아이디</label>
			<input type="text" id="id" name="id" />
		</div>
		<div>
			<label for="pw">패스워드</label>
			<input type="password" id="pw" name="pw"/>
		</div>
		<div>
			<label for="name">이름</label>
			<input type="text" id="name" name="name"/>
		</div>
		<input type="submit" value="등록">
	</form>

*/
package com.example.session.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.session.dto.UserDto;
import com.example.session.service.UserService;
import com.example.session.session.UserSession;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyController {
	@Autowired
	private UserSession userSession;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String root() {
		System.out.println("root.....");
		return "index";
	}
	
	/*
	@GetMapping("/login")
	public String login() {
		System.out.println("login-GET....");
		
		return "login";
	}
	*/
	@GetMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		System.out.println("login-GET....");
		
		Cookie[] cookies = request.getCookies();	
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("loginUserId")) {
				model.addAttribute("userId", cookie.getValue());
			} else if(cookie.getName().equals("loginUserPw")) {
				model.addAttribute("userPw", cookie.getValue());
			}
		}
		
		return "login";
	}
	
	
	/*
	@PostMapping("/login")
	public String login(
		@RequestParam("id") String id,
		@RequestParam("pw") String pw,
		HttpSession session,
		RedirectAttributes rttr,
	) {
		UserDto result = userService.loginCheck(id, pw);
		
		if(result != null) {
			// session.setAttribute("id", id);
			userSession.login(result);
			
			return "redirect:/success";
		} else {
			rttr.addFlashAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다");
			return "redirect:/login";
		}
	}
	*/
	
	@PostMapping("/login")
	public String login(
		@RequestParam("id") String id,
		@RequestParam("pw") String pw,
		@RequestParam(name = "autoLogin", required = false, defaultValue = "false") Boolean autoLogin,
		HttpSession session,
		RedirectAttributes rttr,
		HttpServletResponse response // 쿠키를 사용함
	) {
		UserDto result = userService.loginCheck(id, pw);
		
		if(result != null) {
			// session.setAttribute("id", id);
			userSession.login(result);
			
			// 로그인이 성공이라면 쿠키에 로그인 정보를 저장
			if(autoLogin) {
				Cookie cookieId = new Cookie("loginUserId", userSession.getLoginUser().getId());
				Cookie cookiePw = new Cookie("loginUserPw", userSession.getLoginUser().getPw());
			    cookieId.setPath("/");
			    cookiePw.setPath("/");
				response.addCookie(cookieId);
				response.addCookie(cookiePw);
			} else {
			    // 기존 쿠키 삭제
			    Cookie cookieId = new Cookie("loginUserId", null);
			    Cookie cookiePw = new Cookie("loginUserPw", null);
			    cookieId.setMaxAge(0);
			    cookiePw.setMaxAge(0);
			    cookieId.setPath("/");
			    cookiePw.setPath("/");
			    response.addCookie(cookieId);
			    response.addCookie(cookiePw);
			}
			
			return "redirect:/success";
		} else {
			rttr.addFlashAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다");
			return "redirect:/login";
		}
	}
	
	@GetMapping("/success")
	public String success(RedirectAttributes rttr, Model model) {
		if(userSession.isLogedIn()) {
			model.addAttribute("user", userSession.getLoginUser());
			return "result";
		} else {
			rttr.addFlashAttribute("msg", "로그인을 먼저 진행해주세요");
			return "redirect:/login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes rttr) {
		// session.invalidate();
		userSession.logout();
		rttr.addFlashAttribute("msg", "방금 로그아웃 되었습니다");
		return "redirect:/login";
	}
	
	
	// ==========================================================================
	// 쿠키 객체 사용
	
	@GetMapping("/set-cookie")
	public @ResponseBody String setCookie(HttpServletResponse response) {
		// 새 쿠키 인스턴스를 생성함
		// 키-값		myCookie
		Cookie cookie = new Cookie("myCookie", "쿠키맛나요"); 
		cookie.setPath("/");
		response.addCookie(cookie);
		return "cookie set";
	}
	
	@GetMapping("/read-cookie")
	public @ResponseBody String readCookie(HttpServletRequest request, Model model) {
		Cookie[] cookies = request.getCookies(); // 쿠키들을 배열로 반환 후 받음
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("myCookie")) {
					System.out.println(cookie.getValue());
				}
			}
		}
		return "cookie read";
	}
	
	// 사용자 인증 처리를 쿠키를 사용해서 구현하기
	@GetMapping("/cookie")
	public String cookieResult(HttpServletRequest request, RedirectAttributes rttr, Model model) {
		Cookie[] cookies = request.getCookies();
		String cookieId = "";
		String cookiePw = "";
		
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("loginUserId")) {
				System.out.println(cookie.getValue());
				cookieId = cookie.getValue();
			} else if(cookie.getName().equals("loginUserPw")) {
				System.out.println(cookie.getValue());
				cookiePw = cookie.getValue();	
			}
		}
		
		UserDto result = userService.loginCheck(cookieId, cookiePw);
		
		if(result != null) {
			userSession.login(result);
			model.addAttribute("user", userSession.getLoginUser());
			return "cookie";
		} else {
			rttr.addFlashAttribute("msg", "쿠키에 사용자의 정보가 존재하지 않습니다");
			return "redirect:/login";	
		}
		
	}
	
	
	// =====================================================
	@GetMapping("/cookie-main")
	public String cookieMain() {
		return "cookieMain";
	}
	
	@GetMapping("/cookie-result")
	public String cookieResult() {
		return "cookieResult";
	}
}

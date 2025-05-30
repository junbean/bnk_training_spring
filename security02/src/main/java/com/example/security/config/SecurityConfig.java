package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration			// 이 클래스에서 Bean을 등록함
@EnableWebSecurity		// Spring Security를 활성화하고 SecurityFilterChain을 자동 구성 가능하게 함
public class SecurityConfig {
	// 1. SecurityConfig 클래스가 하는 일
	//		- 이 클래스는 Spring security의 핵심 설정을 담당한다
	//		- 어떤 URL은 누구나 접근할 수 있고, 어떤 URL은 로그인 또는 특정 권한이 있어야 접근 가능한지를 정의한다
	//		- 로그인 처리, 비밀번호 인코딩, 보안 필터 구성 등을 설정한다
	
	// @Bean
	// 		해당 메서드의 리턴 객체를 스프링 빈으로 드옭해서 DI에 활용 가능하게 함
	
	// 2. BCryptPasswordEncoder 설명
	// 		- BCryptPasswordEncode 
	//			- 비밀번호를 암호화(해시)하기 위한 클래스. 
	//			- 내부적으로 Salt 사용. 
	//			- 같은 입력값이라도 해시 결과가 매번 다름
	// 		- 선언 이유
	//			- 로그인 시 사용자가 입력한 비밀번호와 DB의 암호화된 비밀번호를 비교하려면 같은 방식으로 암호화를 해야 함
	// 		- 사용하는 곳
	//			- UserDetailsService 구현체에서 비밀번호를 검증할 때 사용
	//
	//
	@Bean	
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 3. SecurityFilterChain이란?
	//		- SecurityFilterChain은 스프링 시큐리티가 요청을 필터링하는 보안 필터 체인이다
	//		- 인증, 인가, 세션 관리, 로그인 방식 등을 이 체인에서 설정한다
	//		- HttpSecurity를 통해 설정을 구성한 뒤 http.build()로 완성한다
	
	// 4. filterChain()의 파라미터 HttpSecurity는?
	//		- HttpSecurity는 보안 관련 설정을 구성하는 빌더 클래스
	// 		- 주로 다음 항목들을 설정한다
	//			- URL 접근 권한 설정
	//			- 로그인 방식 설정
	//			- 로그아웃 설정
	//			- CSRF, CORS 등 보안 옵션
	

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 6. http에 사용된 메서드 정리
		// 		- authorizeHttpRequests()
		//			- 어떤 URL에 누가 접근 가능한지 인가(Authorization) 를 설정
		//			- FilterSecurityInterceptor 필터를 통해 접근 권한 체크
		//			- URL에 따른 접근 권한을 세부적으로 통제하는 핵심 메서드
		//			- 주요 메서드
		//				- requestMatchers(...) 	: URL 경로 패턴 지정
		//				- anyRequest() 			: 나머지 모든 요청
		//				- ==================================================
		//				- permitAll() : 모두 접근 허용
		//				- hasAnyRole("ROLE1", "ROLE2")	: 해당 권한이 있는 사용자만 허용
		//				- hasRole("ROLE") : 해당 권한(ROLE)이 있는 사용자만 허용
		//				- authenticated() : 인증된 사용자만 허용
		http.authorizeHttpRequests((auth) -> auth 
	            .requestMatchers("/","/regMember","/regist").permitAll()
	            .requestMatchers("/member/**").hasAnyRole("ADMIN","MEMBER")
	            .requestMatchers("/admin").hasRole("ADMIN")
	            .anyRequest().authenticated()
	            );

		// 		- formLogin()
		//			- 사용자 로그인 방식을 폼 기반(form-based) 로그인으로 설정
		//			- UsernamePasswordAuthenticationFilter가 로그인 요청을 처리
		//			- 직접 만든 로그인 페이지를 사용하려면 이 설정이 반드시 필요함
		//			- 주요 메서드
		//				- loginPage("/login")				: 로그인 화면의 경로 지정 (GET 요청)
		//				- loginProcessingUrl("/loginProc")	: 로그인 요청을 처리할 URL (POST 요청)
		//							- 만약 URL을 명시하지 않을 경우에 
		//								- 기본값은 -> POST : /login   
		//							- 기본 경로로 POST요청이 들어오면 
		//								- Spring Security의 내부 필터(UsernamePasswordAuthenticationFilter)가 자동으로 로그인 처리를 수행합니다
		//							- 내부적으로 username, password라는 이름의 파라미터를 찾아 사용한다
		//							- loginProcessingUrl("/loginProc")을 설정한다면 
		//								- 이동경로 -> POST : /loginProc
		//							- Controller 따로 구현하지 않아도 Spring Security가 알아서 자동 처리한다
		//
		//				- defaultSuccessUrl("/home")		: 로그인 성공 시 이동할 경로
		//				- failureUrl("/fail")				: 로그인 실패 시 이동할 경로
		//				- permitAll()						: 로그인 경로는 인증 없이 허용
		http.formLogin((auth) -> auth
	            .loginPage("/login")
	            .loginProcessingUrl("/loginProc")
	            .defaultSuccessUrl("/member/welcome")
	            .failureUrl("/fail")
	            .permitAll()
	            );

		//		- csrf()
		//			- CSRF(Cross-Site Request Forgery) 보호 기능을 설정
		//			- 기본값으로 활성화되어 있음 (보안을 위해 중요)
		//			- disable()을 통해서 테스트나 개발 단계에서 비활성화 가능
		//			- POST, PUT 요청 등에서 CSRF 토큰을 체크하지 않게 설정
		http.csrf(csrf -> csrf.disable());
		
		
		//		- logout()
		//			- 로그아웃 동작을 커스터마이징
		//			- 로그아웃 동작도 원하는 URL과 후처리 경로를 지정 가능
		//			- 주요 메서드
		//				- logoutUrl()		: 로그아웃 처리 URL
		//						- 로그아웃은 기본적으로 POST요청
		//						- Controller 따로 구현하지 않아도 Spring Security가 /logout 경로로 POST요청이 들어오면 LogoutFilter를 통해 자동으로 로그아웃 처리를 한다
		//						- 로그아웃 할때 자동으로 세션과 쿠키를 지우지 않아서 메서드를 붙여서 지워야함
		//						- 
		//				- logoutSuccessUrl(): 로그아웃 성공 후 이동할 경로
		//				- invalidateHttpSession(true)	: 세션 초기화
		http.logout(logout -> logout
			    .logoutUrl("/logout")
			    .logoutSuccessUrl("/")
			    .deleteCookies("JSESSIONID")		// 쿠키는 하나씩 명시해야 지울수 있다, 다 지우는 메서드는 없음
			    .invalidateHttpSession(true)
			);
		
		//		- http.build()
		//			- 앞서 설정한 내용을 기반으로 SecurityFilterChain 객체를 생성
		//			- 반환값은 Spring Security가 사용할 보안 필터 체인 전체 구성
		return http.build();
	}
}

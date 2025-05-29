package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//		// "/" 경로는 모두에게 개방하는 코드
//
//		http.authorizeHttpRequests(auth -> auth.requestMatchers("/").permitAll());
//		
//		// 요청된 모든 경로는 모두에게 개방하는 코드
//		http.authorizeHttpRequests(auth -> auth
//				.anyRequest().permitAll()
//				);
//		
//		return http.build();
//	}
	

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 루트경로(/)에서는 접근을 허용하지만, 그외의 경로에 대해서는 인증을 요구한다
		http.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/").permitAll()
				.anyRequest().authenticated()
				);
		// http.formLogin(Customizer.withDefaults());
		// http.httpBasic(Customizer.withDefaults());
		
		// 
		http.formLogin(auth -> auth
				.loginPage("/login")
				.loginProcessingUrl("/loginProc")
				.defaultSuccessUrl("/welcome")
				.permitAll()
				);
		
		http.csrf(csrf -> csrf.disable());
		
		http.logout(Customizer.withDefaults());		// 기본	/logout 처리
		
		return http.build();
	}
}

/*

클래스에 붙이는거
	Controller
	Service
	Repository
	
해당 메서드에 반환되는 자료가 스프링에 관리된다


*/
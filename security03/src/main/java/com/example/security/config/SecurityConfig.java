package com.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.security.auth.CustomAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// csrf 무효화
		// http.csrf(auth -> auth.disable());
		
		http.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/", "/regist", "/registProc", "/login", "/loginProc").permitAll()
				.requestMatchers("/members/**", "/success").hasAnyRole("MEMBER", "ADMIN")
				.requestMatchers("/admin").hasRole("ADMIN")
	            .anyRequest().authenticated()
				);
		
		http.formLogin((auth) -> auth
				.loginPage("/login")
				.loginProcessingUrl("/loginProc")
				.defaultSuccessUrl("/success")
				.permitAll()
				);
		
		http.logout((auth) -> auth
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.permitAll()
				);
		
		// 예외 처리
		http.exceptionHandling(exception -> exception
				.authenticationEntryPoint(customAuthenticationEntryPoint));
		
		return http.build();
	}
}

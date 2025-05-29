package com.example.jpaBoard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	/*
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
		
		return http.build();
	}
	*/
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/").permitAll()
				.anyRequest().authenticated()
				);
		// http.formLogin(Customizer.withDefaults());
		// http.httpBasic(Customizer.withDefaults());
		
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
사용자 이름
	user
password
	4a329ecf-6cae-4355-884f-0500aa776804

*/
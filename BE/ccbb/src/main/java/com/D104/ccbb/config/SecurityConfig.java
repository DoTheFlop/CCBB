package com.D104.ccbb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.D104.ccbb.jwt.filter.JwtAuthenticationFilter;
import com.D104.ccbb.jwt.service.JwtTokenService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtTokenService jwtTokenService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// csrf, cors 비활성
		http
			.cors()
			.configurationSource(corsConfigurationSource())
			.and()
			.csrf().disable()

			//세선 사용안하므로 비활성
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			// 시큐리티 기본 로그인 끄기
			.and()
			.formLogin().disable()
			.httpBasic().disable()
			// 권한 설정
			.authorizeRequests()
			// 로그인, 회원가입, 파일 접근은 권한 개방
			.antMatchers("/", "/user/login", "/user/signup", "/oauth/*", "/file/*").permitAll()
			// 그 이외에는 인증된 유저만 접근
			.anyRequest().authenticated()
			.and()
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenService), UsernamePasswordAuthenticationFilter.class);
		// 이후 jwt 인증을 위한 커스텀 필터 등록

		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedMethod("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedOrigin("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}
}

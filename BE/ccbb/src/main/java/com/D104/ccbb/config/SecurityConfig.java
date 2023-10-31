package com.D104.ccbb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.D104.ccbb.jwt.filter.JwtAuthenticationFilter;
import com.D104.ccbb.jwt.service.JwtTokenService;
import com.D104.ccbb.oauth2.handler.OAuth2LoginFailureHandler;
import com.D104.ccbb.oauth2.handler.OAuth2LoginSuccessHandler;
import com.D104.ccbb.oauth2.service.CustomOAuth2UserService;
import com.D104.ccbb.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtTokenService jwtTokenService;
	private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
	private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
	private final CustomOAuth2UserService customOAuth2UserService;
	private final ObjectMapper objectMapper;
	private final UserRepository userRepository;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// csrf, cors 비활성
		http
			.cors()
			.configurationSource(corsConfigurationSource())
			.and()
			.csrf().disable()

			//세션 사용안하므로 비활성
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			// 시큐리티 기본 로그인 끄기
			.and()
			.formLogin().disable()
			.httpBasic().disable()
			// 권한 설정
			.authorizeRequests()
			// 로그인, 회원가입, 파일 접근은 권한 개방
			.antMatchers("/", "/profileimg/**", "/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs/**",
				"/user/signup", "/oauth2/**", "/user/esign-up", "/user/elogin",
				"/file/*").permitAll()
			// 그 이외에는 인증된 유저만 접근
			.antMatchers(HttpMethod.GET).permitAll()
			.anyRequest().authenticated()
			.and()
			.addFilterAfter(jwtAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
			//== 소셜 로그인 설정 ==//
			.oauth2Login()
			.successHandler(oAuth2LoginSuccessHandler) // 동의하고 계속하기를 눌렀을 때 Handler 설정
			.failureHandler(oAuth2LoginFailureHandler) // 소셜 로그인 실패 시 핸들러 설정
			.userInfoEndpoint().userService(customOAuth2UserService); // customUserService 설정
		// 이후 jwt 인증을 위한 커스텀 필터 등록

		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedMethod("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedOriginPattern("*");
		corsConfiguration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	//[PART 5]
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationProcessingFilter() {
		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtTokenService, userRepository);
		return jwtAuthenticationFilter;
	}

}

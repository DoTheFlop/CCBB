package com.D104.ccbb.jwt.service;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.D104.ccbb.user.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Getter
@RequiredArgsConstructor
public class JwtTokenService implements InitializingBean {

	private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
	private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";

	@Value("${jwt.secretKey}")
	private String secret;

	private final long tokenValidityInMilliseconds = 86400000;
	private final long refreshTokenExpirationPeriod = 86400000;
	private Key key;

	private String accessHeader = "Authorization";
	private String refreshHeader = "Authorization-refresh";
	private final UserRepository userRepository;

	@Override
	public void afterPropertiesSet() {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	// 토큰 생성
	public String createToken(String email) {
		log.info("createToken email: {}", email);
		long now = (new Date()).getTime();
		Date validity = new Date(now + this.tokenValidityInMilliseconds);

		return Jwts.builder()
			.setSubject(ACCESS_TOKEN_SUBJECT)
			.claim("email", email)
			.signWith(key, SignatureAlgorithm.HS512)
			.setExpiration(validity)
			.compact();
	}

	/**
	 * RefreshToken 생성
	 * RefreshToken은 Claim에 email도 넣지 않으므로 withClaim() X
	 */
	public String createRefreshToken() {
		Date now = new Date();
		return Jwts.builder()
			.setSubject(REFRESH_TOKEN_SUBJECT)
			.signWith(key, SignatureAlgorithm.HS512)
			.setExpiration(new Date(now.getTime() + refreshTokenExpirationPeriod))
			.compact();

		// JWT.create()
		// 	.withSubject(REFRESH_TOKEN_SUBJECT)
		// 	.withExpiresAt(new Date(now.getTime() + refreshTokenExpirationPeriod))
		// 	.sign(Algorithm.HMAC512(secret));
	}

	// todo: 추후 claim에서 값 가져오는 메서드로 수정할 것
	public String getUserEmail(String token) {
		log.info(token);
		Claims claims = Jwts
			.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody();

		return (String)claims.get("email");
	}

	/**
	 * RefreshToken DB 저장(업데이트)
	 */
	// public void updateRefreshToken(String email, String refreshToken) {
	// 	userRepository.findByEmail(email)
	// 		.ifPresentOrElse(
	// 			user -> user.updateRefreshToken(refreshToken),
	// 			() -> new Exception("일치하는 회원이 없습니다.")
	// 		);
	// }
	public String extractToken(String token) {
		if (StringUtils.hasText(token) && (token.startsWith("Bearer ") || token.startsWith("bearer "))) {
			return token.substring(7);
		}
		return null;
	}

	/**
	 * 헤더에서 RefreshToken 추출
	 * 토큰 형식 : Bearer XXX에서 Bearer를 제외하고 순수 토큰만 가져오기 위해서
	 * 헤더를 가져온 후 "Bearer"를 삭제(""로 replace)
	 */
	public Optional<String> extractRefreshToken(HttpServletRequest request) {
		return Optional.ofNullable(request.getHeader(refreshHeader))
			.filter(refreshToken -> refreshToken.startsWith("Bearer ") || refreshToken.startsWith("bearer "))
			.map(refreshToken -> refreshToken.substring(7));
	}

	public Optional<String> extractAccessToken(HttpServletRequest request) {
		return Optional.ofNullable(request.getHeader(accessHeader))
			.filter(refreshToken -> refreshToken.startsWith("Bearer ") || refreshToken.startsWith("bearer "))
			.map(refreshToken -> refreshToken.substring(7));
	}

	// 토큰이 유효한지 검증
	public boolean validateToken(String token) {
		log.info("validateToken: {}", token);
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			log.info("잘못된 JWT 서명입니다.");
		} catch (ExpiredJwtException e) {
			log.info("만료된 JWT 토큰입니다.");
		} catch (UnsupportedJwtException e) {
			log.info("지원되지 않는 JWT 토큰입니다.");
		} catch (IllegalArgumentException e) {
			log.info("JWT 토큰이 잘못되었습니다.");
		}
		return false;
	}

	public void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken, String refreshToken) {
		response.setStatus(HttpServletResponse.SC_OK);

		setAccessTokenHeader(response, accessToken);
		setRefreshTokenHeader(response, refreshToken);
		log.info("Access Token, Refresh Token 헤더 설정 완료");
	}

	/**
	 * AccessToken 헤더 설정
	 */
	public void setAccessTokenHeader(HttpServletResponse response, String accessToken) {
		response.setHeader(accessHeader, accessToken);
	}

	/**
	 * RefreshToken 헤더 설정
	 */
	public void setRefreshTokenHeader(HttpServletResponse response, String refreshToken) {
		response.setHeader(refreshHeader, refreshToken);
	}

}
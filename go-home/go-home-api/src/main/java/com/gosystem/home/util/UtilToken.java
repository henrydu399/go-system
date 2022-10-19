package com.gosystem.home.util;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class UtilToken {

	@Value("${jwt.key}")
	private String secretKey;

	public static final String HEADER_STRING = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";

	public String[] getRoles(HttpServletRequest req) {
		String[] roles = null;
		try {
			String token = req.getHeader(HEADER_STRING).replace(TOKEN_PREFIX, "");
			Claims claims = getAllClaimsFromToken(token);
			roles = (String[]) claims.get("authorities");
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return roles;

	}

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(this.secretKey.getBytes()).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

}

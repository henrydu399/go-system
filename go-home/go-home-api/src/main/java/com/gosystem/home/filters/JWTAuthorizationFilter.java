package com.gosystem.home.filters;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.utils.UtilsLogs;
import com.gosystem.home.services.imp.UserServiceImpl;
import com.gosystem.home.util.BCryptPasswordEncoder;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private final String HEADER = "Authorization";
	private final String PREFIX = "Bearer ";

	@Value("${jwt.key}")
	private String secretKey;

	private Logger logger;

	public JWTAuthorizationFilter() {
		logger = UtilsLogs.getLogger(UserServiceImpl.class.getName());
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		try {

			String pathInfo = request.getRequestURL().toString();
			if (pathInfo.contains("home")) {
				if (!pathInfo.contains("public")) {

					if (!pathInfo.contains("login") || !pathInfo.contains("confirm")) {

						if (existeJWTToken(request, response)) {
							Claims claims = validateToken(request);
							if (Objects.nonNull(claims) && claims.get("authorities") != null) {
								setUpSpringAuthentication(claims);
							} else {
								SecurityContextHolder.clearContext();
								logger.info("EL TOKEN NO TIENE LA PROPIEDAD authorities ,");
							}
						} else {
							logger.info("NO EXISTE TOKEN Y EL RECURSO NO ES LOGIN NI HOME ,");
							SecurityContextHolder.clearContext();
						}
					}
				}
			}

			chain.doFilter(request, response);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
			logger.info("ERROR CON EL TOKEN " );
			logger.severe(e.getMessage());
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			return;
		}
	}

	private Claims validateToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
		return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(jwtToken).getBody();
	}

	/**
	 * Metodo para autenticarnos dentro del flujo de Spring
	 * 
	 * @param claims
	 */
	private void setUpSpringAuthentication(Claims claims) {
		@SuppressWarnings("unchecked")
		List<String> authorities = (List) claims.get("authorities");

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
				authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
		SecurityContextHolder.getContext().setAuthentication(auth);

	}

	private boolean existeJWTToken(HttpServletRequest request, HttpServletResponse res) {
		String authenticationHeader = request.getHeader(HEADER);
		if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
			return false;
		return true;
	}

}

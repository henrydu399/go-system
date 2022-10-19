package com.gosystem.home.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.gosystem.home.filters.JWTAuthorizationFilter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JWTAuthorizationFilter jWTAuthorizationFilter;
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		 http.cors()
		 .and()
		 .csrf().disable()
		 .authorizeRequests()	
		 .antMatchers(HttpMethod.POST, "/login/**").permitAll()
		 .antMatchers(HttpMethod.POST, "/usuario/**").permitAll()
		 .antMatchers(HttpMethod.GET, "/usuario/**").permitAll()
		 .antMatchers(HttpMethod.GET, "/ciudad/**").permitAll()
		 .antMatchers(HttpMethod.GET, "/departamento/**").permitAll()
		 .antMatchers(HttpMethod.GET, "/barrio/**").permitAll()
		 //.antMatchers(HttpMethod.POST, "/usuario/login").permitAll()
		 .antMatchers(HttpMethod.GET, "/files/**").permitAll()
		 .anyRequest().authenticated()
		 .and()
		 .addFilterBefore(jWTAuthorizationFilter, UsernamePasswordAuthenticationFilter.class) ;
          

	} 
	


	
}

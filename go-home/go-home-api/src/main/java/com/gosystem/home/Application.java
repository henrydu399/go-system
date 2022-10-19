package com.gosystem.home;

import java.util.Arrays;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;






@EnableEurekaClient
@SpringBootApplication
public class Application {
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);	
		
	}
	/*
	 * @Bean CorsConfigurationSource corsConfigurationSource() { CorsConfiguration
	 * configuration = new CorsConfiguration();
	 * configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
	 * configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH", "PUT",
	 * "DELETE", "OPTIONS", "HEAD")); configuration.setAllowCredentials(true);
	 * configuration.setAllowedHeaders(Arrays.asList("Authorization",
	 * "Requestor-Type"));
	 * configuration.setExposedHeaders(Arrays.asList("X-Get-Header"));
	 * configuration.setMaxAge(3600L); UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource(); source.registerCorsConfiguration("/**",
	 * configuration); return source; }
	 */
	
	 @PostConstruct
	    public void init(){
	      TimeZone.setDefault(TimeZone.getTimeZone("America/Bogota"));
	    }
	

}

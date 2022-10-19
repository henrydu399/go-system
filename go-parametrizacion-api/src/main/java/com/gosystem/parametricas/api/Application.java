package com.gosystem.parametricas.api;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;



@EnableEurekaClient
@SpringBootApplication
public class Application {
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);	
		
	}
	
	
	
	 @PostConstruct
	    public void init(){
	      TimeZone.setDefault(TimeZone.getTimeZone("America/Bogota"));
	    }
	

}

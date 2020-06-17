package com.tyss.cg.springbootdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LoginRootModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginRootModuleApplication.class, args);
	}
	
	@Bean
	public  RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}

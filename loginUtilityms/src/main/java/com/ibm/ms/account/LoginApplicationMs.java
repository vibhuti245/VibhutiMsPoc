package com.ibm.ms.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.ibm.ms.account.model.UserDTO;
import com.ibm.ms.account.service.JwtUserDetailsService;

@EnableEurekaClient
@SpringBootApplication
public class LoginApplicationMs {
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
		
	public static void main(String[] args) {
		SpringApplication.run(LoginApplicationMs.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			UserDTO user = new UserDTO();
			user.setUsername("admin");
			user.setPassword("admin");
			userDetailsService.save(user);
			
			/*
			 * user = new UserDTO(); user.setUsername("guest"); user.setPassword("guest");
			 * userDetailsService.save(user);
			 * 
			 * user = new UserDTO(); user.setUsername("user"); user.setPassword("password");
			 * userDetailsService.save(user);
			 */
		};
	}
}

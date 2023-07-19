package com.organizemanagemodule.paylods;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtAthResponse {
	
	
	private String token;
	
	@Bean
	String AthResponse() {
		return token;
	}
	
	

}

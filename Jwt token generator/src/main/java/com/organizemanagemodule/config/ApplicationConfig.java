package com.organizemanagemodule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.web.context.NullSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfig {
	
	@Bean
    SecurityContextRepository securityContextRepository(){
        return new NullSecurityContextRepository(); // I use Null Repository since I don't need it for anything except store information in UserDetails
    }

}

package com.organizemanagemodule.userdetalis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.organizemanagemodule.repo.UserRepo;

@Configuration
public class CustomUserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Bean
	UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
				UserDetails user = userRepo.findByEmail(email)
						.orElseThrow(() -> new UsernameNotFoundException("User not found"));
				if (user == null) {
					throw new UsernameNotFoundException("User not found");
				}
				return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
						user.getAuthorities());
			}
		};
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

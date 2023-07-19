package com.organizemanagemodule.services.impl;

import com.organizemanagemodule.entities.ERole;
import com.organizemanagemodule.entities.RefreshTokenGenerator;
import com.organizemanagemodule.entities.Role;
import com.organizemanagemodule.entities.User;
import com.organizemanagemodule.exceptions.UserNotFoundException;
import com.organizemanagemodule.paylods.AuthResponse;
import com.organizemanagemodule.paylods.JwtAthRequest;
import com.organizemanagemodule.paylods.LoginResponse;
import com.organizemanagemodule.paylods.RegisterRequest;
import com.organizemanagemodule.repo.RoleRepo;
import com.organizemanagemodule.repo.UserRepo;
import com.organizemanagemodule.security.JwtUtil;
import com.organizemanagemodule.services.RefreshTokenService;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AthService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private RefreshTokenService refreshTokenService;
	@Observed(name = "register.post.user")
	public AuthResponse register(RegisterRequest request) {

		AuthResponse response = new AuthResponse();
		
//		Set<String> strRoles = request.getRoles();
		Set<String> strRoles = new HashSet<>();
		strRoles.add("STUDENT");
		
		Set<Role> roles = new HashSet<>();
		
        strRoles.forEach(role->{
			switch (role) {
				case "ADMIN" -> {
					Role adminRole = roleRepo.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new IllegalStateException("Role not found of Admin"));
					roles.add(adminRole);
				}
				case "STUDENT" -> {
					Role studentRole = roleRepo.findByName(ERole.ROLE_STUDENT).orElseThrow(() -> new IllegalStateException("Role not found of student"));
					roles.add(studentRole);
				}
				case "TEACHER" -> {
					Role teacherRole = roleRepo.findByName(ERole.ROLE_TEACHER).orElseThrow(() -> new IllegalStateException("Role not found of teacher"));
					roles.add(teacherRole);
				}
				case "EMPLOYEE" -> {
					Role employeeRole = roleRepo.findByName(ERole.ROLE_EMPLOYEE).orElseThrow(() -> new IllegalStateException("Role not found of employee"));
					roles.add(employeeRole);
				}
			}
        });

		    var	user = User.builder()
					.uuid(UUID.randomUUID().toString())
					.email(request.getEmail())
					.password(encoder.encode(request.getPassword()))
					.roless(roles)
					.build();
			this.userRepo.save(user);
			var jwtToken = jwtUtil.generateToken(user);
			response.setEmail(user.getEmail());
			response.setUuId(user.getUuid());
			response.setToken(jwtToken);

		return response;
	}
	@Observed(name = "login.user")
	public LoginResponse authenticate(JwtAthRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		var user = this.userRepo.findByEmail(request.getUsername()).orElseThrow(()->new UserNotFoundException("email", 0));
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		var jwtToken = this.jwtUtil.generateToken(userDetails);
		RefreshTokenGenerator refreshToken = refreshTokenService.createToken(request.getUsername());
		return LoginResponse.builder()
				.token(jwtToken)
				.refreshToken(refreshToken.getToken())
				.uuId(user.getUuid())
				.email(user.getUsername())
				.build();
	}

}

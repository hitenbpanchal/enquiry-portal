package com.organizemanagemodule.controller;

import com.organizemanagemodule.paylods.*;
import com.organizemanagemodule.repo.UserRepo;
import com.organizemanagemodule.security.JwtUtil;
import com.organizemanagemodule.services.UserService;
import com.organizemanagemodule.services.impl.AthService;
import com.organizemanagemodule.services.impl.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
		
	@Autowired
	private AthService athService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UserService userService;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private ResetPasswordService resetPasswordService;

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)  {
		return ResponseEntity.ok(athService.register(request));
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authenticate(@RequestBody JwtAthRequest request){
		return ResponseEntity.ok(athService.authenticate(request));
	}

	@PostMapping("/verify")
	public AuthenticateUserResponse verifyToken(@RequestBody String request){
		return jwtUtil.verifyToken(request);
	}

	@GetMapping("/current-user")
	public UserDetails getCurrentUser(Principal principal){
		return this.userDetailsService.loadUserByUsername(principal.getName());
	}
	@PostMapping("/is-present")
	public Boolean isUserPresent(@RequestBody String email){
		return this.userService.isUserPresent(email);
	}

	@PostMapping("/sent-otp")
	public ResponseEntity<String> sentOTP(@RequestParam("email") String email){
		return ResponseEntity.ok(this.resetPasswordService.sentOTP(email));
	}
	@PostMapping("/verify-otp/{password}")
	public ResponseEntity<Boolean> verifyOTP(@PathVariable("password") Integer password) throws ExecutionException {
		return ResponseEntity.ok(this.resetPasswordService.verifyOTP(password));
	}
}

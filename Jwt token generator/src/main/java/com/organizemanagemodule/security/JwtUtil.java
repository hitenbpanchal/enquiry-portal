package com.organizemanagemodule.security;

import com.organizemanagemodule.exceptions.UnAuthenticatedUser;
import com.organizemanagemodule.paylods.AuthenticateUserResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.organizemanagemodule.repo.UserRepo;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserRepo userRepo;

	private final int timeout = 60*1000;

	private final String SECRET_KEY = "secret";

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String subject) {
//    	Jwts.builder().setClaims(claims).setSubject(subject).claim("authorities", userDetailsService.loadUserByUsername(subject).getAuthorities()).setIssuedAt(null)
//    	List<String> authorities = new ArrayList<>();
		return Jwts.builder().setClaims(claims).setSubject(subject)
				.claim("authorities", userDetailsService.loadUserByUsername(subject).getAuthorities())
				.claim("UUID",
						userRepo.findByEmail(subject).orElseThrow(() -> new IllegalStateException("User not found"))
								.getUuid())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + timeout))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	public Boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public AuthenticateUserResponse verifyToken(String token){
		String email;
		String uuId;
		Object authorities;
		try{
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
		}catch (Exception e){
			throw new UnAuthenticatedUser();
		}

//		Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
//		email = claimsJws.getBody().getSubject();
//		uuId = claimsJws.getBody().getOrDefault("UUID", "").toString();
//		authorities = claimsJws.getBody().get("authorization");
		AuthenticateUserResponse response = new AuthenticateUserResponse();
		response.setMessage("User is authenticated");
		response.setSuccess(true);
		return response;
	}
}

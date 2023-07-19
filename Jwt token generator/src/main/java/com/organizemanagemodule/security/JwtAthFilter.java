package com.organizemanagemodule.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAthFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	private final SecurityContextRepository securityContextRepository;

	private final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response,
			@Nonnull FilterChain filterChain) throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");
		final String userEmail;
		final String jwtToken;

		if (authHeader == null || !authHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}

		jwtToken = authHeader.substring(7);
		userEmail = jwtUtil.extractUsername(jwtToken);

		if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
//			User user = this.userRepo.findByEmail(userEmail).orElseThrow(()-> new IllegalStateException("User Not Found"));

			final boolean isTokenValid = jwtUtil.isTokenValid(jwtToken, userDetails);

			if (isTokenValid) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				SecurityContext securityContext = SecurityContextHolder.getContext();
				securityContext.setAuthentication(authenticationToken);
				SecurityContextHolder.setContext(securityContext);

				securityContextRepository.saveContext(securityContext, request, response);
			} else {
//				logger.info("Token is not valid");
				System.out.println("Token is not Valid");
			}

		} else {
//			logger.info("Email j null che yaa security context holder null nati");
			System.out.println("Email j null che yaa security context holder null nati");
		}

		filterChain.doFilter(request, response);

	}

}

package com.employee.demo.security;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.employee.demo.helper.JwtTokenHelper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenHelper JwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

// It will call whenever hit apirequest..
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// step-1 Here we checking token details or get token.
		String token = null;
		String emailId = null;
		String requestToken = request.getHeader("Authorization");

		if (requestToken != null && requestToken.startsWith("Bearer ")) {
			token = requestToken.substring(7); // getting token without bearer
			try {
				emailId = JwtTokenHelper.getEmailIdFromToken(token);
			} catch (IllegalArgumentException e) {
				System.out.println("unable to get jwt token.");
			} catch (ExpiredJwtException e) {
				System.out.println("jwt token has expired.");
			} catch (MalformedJwtException e) {
				System.out.println("Invalid jwt token. !!");
			}
		} else {
			System.out.println("jwt token does not begin with bearer.");
		}

		// step-2 Once we get the token, now validate the token.
		if (emailId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(emailId);
			try {
				if (JwtTokenHelper.validateToken(token, emailId)) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());

					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				} else {
					System.out.println("invalid jwt token.");
				}
			} catch (ExpiredJwtException e) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token has expired");
				return;
			}
		}
		
		filterChain.doFilter(request, response);

	}

}

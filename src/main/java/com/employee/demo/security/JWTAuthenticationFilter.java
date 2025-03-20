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

import com.employee.demo.helper.JWTHelper;

import io.jsonwebtoken.ExpiredJwtException;


@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    private JWTHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = null;
		String emailId = null;
		
		String header = request.getHeader("Authorization");
		if(header != null && header.startsWith("Bearer ")) {
			token = header.substring(7);
			emailId = jwtHelper.getEmailIdFromToken(token);
	}

		 if (emailId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails = userDetailsService.loadUserByUsername(emailId);
	            try {
	            if (jwtHelper.validateToken(token, emailId)) {
	                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
	                        userDetails, null, userDetails.getAuthorities());
	                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authentication);
	            }
	            } catch(ExpiredJwtException e) {
	            	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token has expired");
	                return;
	            	}
	            }
        filterChain.doFilter(request, response);
    }

}

package com.employee.demo.security;

import java.io.IOException;
 
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
 
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{
 
// It will excutes when unauthorized user try to access authorized api.
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
	 //sending unauthorized error 
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied !! ");
		
	}

}



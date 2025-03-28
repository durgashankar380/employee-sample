package com.employee.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepository;
import com.employee.demo.security.JWTAuthenticationEntryPoint;
import com.employee.demo.security.JWTAuthenticationFilter;

@Configuration
public class SecurityConfig {
	    
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JWTAuthenticationFilter filter, JWTAuthenticationEntryPoint entryPoint) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/login",
                		"/auth/register",
                		"/swagger-ui/**",
                		"/v3/api-docs/**",
                		"/swagger-resources/**",
                		"/swagger-ui.html",
                		"/webjars/**"
                		).permitAll()
                .requestMatchers("/employee-pagination/**").authenticated()
                .anyRequest().authenticated()
            )
            .exceptionHandling(ex -> ex.authenticationEntryPoint(entryPoint))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
	
	@Bean
	public UserDetailsService userDetailsService(EmployeeRepository employeeRepository) {
	    return emailId -> {
	        Employee employee = employeeRepository.findByEmailId(emailId)
	        		.orElseThrow(() -> new UsernameNotFoundException("User not found with emailId:"+emailId));
//	        if (employee == null) {
//	            throw new UsernameNotFoundException("User not found with emailId: " + emailId);
//	        }
	        

	        return new org.springframework.security.core.userdetails.User(
	            employee.getEmailId(),
	            employee.getPassword(),
	            java.util.Collections.emptyList()
	        );
	    };
	}

	 
	 @Bean
	    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
	        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        provider.setUserDetailsService(userDetailsService);
	        provider.setPasswordEncoder(passwordEncoder());
	        return provider;
	    }
	 
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }
}

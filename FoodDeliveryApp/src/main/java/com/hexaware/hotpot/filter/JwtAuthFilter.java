package com.hexaware.hotpot.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hexaware.hotpot.config.CustomerAdminInfoDetailsService;
import com.hexaware.hotpot.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	CustomerAdminInfoDetailsService userDetailsService;
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		  String authHeader = request.getHeader("Authorization");
	        String token = null;
	        String username = null;
	        if (authHeader != null && authHeader.startsWith("Bearer ")) {
	            token = authHeader.substring(7);
	            username = jwtService.extractUsername(token);
	        }
	        
	        
	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	            if (jwtService.validateToken(token, userDetails)) {
	               
	            	UsernamePasswordAuthenticationToken authToken =
	                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	             
	            	authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authToken);
	                
	             // Get user role and customer ID from token claims
	                String userRole = jwtService.extractClaim(token, claims -> claims.get("role", String.class));
	                Long customerId = jwtService.extractClaim(token, claims -> claims.get("customerId", Long.class));

	                // Add user role and customer ID to the response headers
	                response.setHeader("user-role", userRole);
	                response.setHeader("customer-id", String.valueOf(customerId));
	            }
	        }
	        filterChain.doFilter(request, response);
	    
		
		
	}

}

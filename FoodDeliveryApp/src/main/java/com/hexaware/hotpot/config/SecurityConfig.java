package com.hexaware.hotpot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hexaware.hotpot.filter.JwtAuthFilter;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	
	@Autowired
	JwtAuthFilter authFilter;
	
    @Bean
    //authentication
    public UserDetailsService userDetailsService() {
		
       return new CustomerAdminInfoDetailsService();
    }
    
    @Bean
    public  SecurityFilterChain   getSecurityFilterChain(HttpSecurity http) throws Exception {
    	
    		return http.csrf().disable()
    			.authorizeHttpRequests().requestMatchers("api/customers/register","/api/admin/login/authenticate","/api/customers/login/authenticate").permitAll()
    			.and()
    			.authorizeHttpRequests().requestMatchers("api/admin/**","api/customers/**","api/menuitem/**","api/cart/**","/api/order/**","/api/restaurant/**")
    			.authenticated().and()   //.formLogin().and().build();
    			.sessionManagement()
    			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    			.and()
    			.authenticationProvider(authenticationProvider())
    			.addFilterBefore(authFilter	, UsernamePasswordAuthenticationFilter.class)
    			.build();
    	
    }
    
     
    
    

    @Bean    
    public PasswordEncoder passwordEncoder() {          
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    	
    	return config.getAuthenticationManager();
    	
    }
    
    
    
}

package com.hexaware.hotpot.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hexaware.hotpot.entities.Customers;


public class CustomerInfoDetails implements UserDetails {


    private String name;
    private String password;
    private List<GrantedAuthority> authorities;
    private String role; // Add user role attribute
    private Long customerId; // Add customer ID attribute

    public CustomerInfoDetails(Customers customer, String role, Long customerId) {
        name=customer.getUsername();
        password=customer.getPassword();
        authorities= Arrays.stream(customer.getRole().split(","))
                .map(SimpleGrantedAuthority::new) // .map(str -> new SimpleGrantedAuthority(str))
                .collect(Collectors.toList());
        this.role = role; // Set user role
        this.customerId = customerId; // Set customer ID
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public String getRole() {
        return role;
    }

    public Long getCustomerId() {
        return customerId;
    }
}

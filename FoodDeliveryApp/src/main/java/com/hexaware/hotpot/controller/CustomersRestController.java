package com.hexaware.hotpot.controller;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.hotpot.dto.AuthRequest;
import com.hexaware.hotpot.dto.CustomersDTO;
import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.Discount;
import com.hexaware.hotpot.entities.Restaurants;
import com.hexaware.hotpot.exception.CustomerNotFoundException;
import com.hexaware.hotpot.exception.DiscountNotFoundException;
import com.hexaware.hotpot.exception.LocationNotFoundException;
import com.hexaware.hotpot.repository.CustomersRepository;
import com.hexaware.hotpot.services.ICustomerService;
import com.hexaware.hotpot.services.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/customers")
public class CustomersRestController {
	
    private static final Logger log = LoggerFactory.getLogger(CustomersRestController.class);

	
	@Autowired
	ICustomerService customerService;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	CustomersRepository customerRepo;
	
	
	
	@PostMapping("/login/authenticate")
    
	 public String  authenticateAndGetTokent(@RequestBody  AuthRequest authRequest) {
		 
		
		 
		Authentication authentication = 	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		 
		String token = null;
		
				if(authentication.isAuthenticated()) {
				
					 Optional<Customers> customer = customerRepo.findByUsername(authRequest.getUsername());

				        if (customer.isPresent()) {
				            String role = customer.get().getRole();
				            Long customerId = customer.get().getCustId();

				           
				            token = jwtService.generateToken(authRequest.getUsername(), role, customerId);

				            log.info(token);
				        } else {
				            log.error("User not found in the database");
				           
				        }
					
				}
				else {
					
					log.info("invalid");
					
					 throw new UsernameNotFoundException("UserName or Password in Invalid / Invalid Request");
					
				}
		
				return token;
		 
	 }
	
	@PostMapping("/register")
    
	public String registerCustomers(@RequestBody @Valid CustomersDTO customerDTO) {
		long customerId = customerService.registerCustomer(customerDTO);
		
		if(customerId != 0) {
			return "customer added successfully ";
		}
		else {
			return "failed to add customer ";
		}
	}
	
	@PutMapping("/update-info/{customerId}")
    @PreAuthorize("hasAuthority('customer')")
	public long updateCustomer(@PathVariable long customerId,  @RequestBody CustomersDTO updatedCustomerDTO) throws CustomerNotFoundException {

		
		return customerService.updateCustomer(customerId, updatedCustomerDTO);
		
	}
	
	
	@GetMapping("/getrestaurantByLocation/{location}")
    @PreAuthorize("hasAuthority('customer')")
	public List<Restaurants> getRestaurantByLocation(@PathVariable String location) throws LocationNotFoundException {
		
		
		List<Restaurants> restaurants = customerService.getRestaurantByLocation(location);
	    log.info("Retrieved restaurants for location {}: {}", location, restaurants);
	    return restaurants;
		
	}
	
	@GetMapping("/apply-discount")
    @PreAuthorize("hasAuthority('customer')")
	public Discount getActiveDiscount(@RequestParam("currentDate") LocalDate currentDate) throws DiscountNotFoundException {

		return  customerService.findActiveDiscount(currentDate);
	    

	}

	

}

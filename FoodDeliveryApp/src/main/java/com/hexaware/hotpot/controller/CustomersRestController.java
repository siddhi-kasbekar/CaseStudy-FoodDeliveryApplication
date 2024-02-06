package com.hexaware.hotpot.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.hotpot.dto.CustomersDTO;
import com.hexaware.hotpot.entities.Restaurants;
import com.hexaware.hotpot.services.ICustomerService;

@RestController
@RequestMapping("api/customers")
public class CustomersRestController {
	
    private static final Logger log = LoggerFactory.getLogger(CustomersRestController.class);

	
	@Autowired
	ICustomerService customerService;
	
	@PostMapping("/register")
	public String registerCustomers(@RequestBody CustomersDTO customerDTO) {
		long customerId = customerService.registerCustomer(customerDTO);
		
		if(customerId != 0) {
			return "customer added successfully ";
		}
		else {
			return "failed to add customer ";
		}
	}
	
	@PutMapping("/update-info/{customerId}")
	public long updateCustomer(@PathVariable long customerId,  @RequestBody CustomersDTO updatedCustomerDTO) {
		
		return customerService.updateCustomer(customerId, updatedCustomerDTO);
		
	}
	
	
	@GetMapping("/getrestaurantByLocation/{location}")
	public List<Restaurants> getRestaurantByLocation(@PathVariable String location){
		
		//return customerService.getRestaurantByLocation(location);
		List<Restaurants> restaurants = customerService.getRestaurantByLocation(location);
	    log.info("Retrieved restaurants for location {}: {}", location, restaurants);
	    return restaurants;
		
	}

}

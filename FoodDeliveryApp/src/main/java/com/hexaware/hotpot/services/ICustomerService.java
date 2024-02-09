package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.dto.CustomersDTO;
import com.hexaware.hotpot.entities.Restaurants;
import com.hexaware.hotpot.exception.CustomerNotFoundException;
import com.hexaware.hotpot.exception.LocationNotFoundException;

public interface ICustomerService {

	public long registerCustomer(CustomersDTO customer);
	

	public long updateCustomer(long customerId, CustomersDTO updatedCustomerDTO) throws CustomerNotFoundException;

	public String loginCustomer(CustomersDTO customer);
	
	public List<Restaurants> getRestaurantByLocation(String location) throws LocationNotFoundException;

}

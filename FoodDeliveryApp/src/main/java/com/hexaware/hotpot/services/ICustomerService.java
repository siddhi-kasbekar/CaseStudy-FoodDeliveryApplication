package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.dto.CustomersDTO;
import com.hexaware.hotpot.entities.Restaurants;

public interface ICustomerService {

	public long registerCustomer(CustomersDTO customer);
	
	public long updateCustomer(long customerId, CustomersDTO updatedCustomerDTO) ;

	public String loginCustomer(CustomersDTO customer);
	
	public List<Restaurants> getRestaurantByLocation(String location);
}

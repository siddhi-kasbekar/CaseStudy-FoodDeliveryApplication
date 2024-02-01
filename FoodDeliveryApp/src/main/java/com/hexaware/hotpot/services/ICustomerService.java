package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.Restaurants;

public interface ICustomerService {

	public long registerCustomer(Customers customer);

	public String loginCustomer(Customers customer);
	public List<Restaurants> getRestaurantByLocation(String location);
}

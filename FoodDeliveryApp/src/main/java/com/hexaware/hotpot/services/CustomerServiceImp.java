package com.hexaware.hotpot.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.hotpot.dto.CustomersDTO;
import com.hexaware.hotpot.entities.Restaurants;

@Service
public class CustomerServiceImp implements ICustomerService {

	@Override
	public long registerCustomer(CustomersDTO customer) {
		
		return 0;
	}

	@Override
	public String loginCustomer(CustomersDTO customer) {
		
		return null;
	}

	@Override
	public List<Restaurants> getRestaurantByLocation(String location) {
		// TODO Auto-generated method stub
		return null;
	}

}

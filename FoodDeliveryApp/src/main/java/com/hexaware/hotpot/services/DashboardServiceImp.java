package com.hexaware.hotpot.services;

import org.springframework.stereotype.Service;

import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.Restaurants;

@Service
public class DashboardServiceImp implements IDashboardService {

	@Override
	public Customers getUserDashboard(Long customerId) {
		
		return null;
	}

	@Override
	public Restaurants getRestaurantDashboard(Long restaurantId) {
		
		return null;
	}

}

package com.hexaware.hotpot.services;

import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.Restaurants;

public interface IDashboardService {

	public Customers getUserDashboard(Long customerId);

	public Restaurants getRestaurantDashboard(Long restaurantId);
}

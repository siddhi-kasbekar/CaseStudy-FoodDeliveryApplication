package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.entities.Administrator;
import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.entities.Restaurants;

public class AdminServiceImp implements IAdminService {

	@Override
	public String adminLogin(Administrator admin) {
		
		return null;
	}

	@Override
	public Restaurants addRestaurant(Restaurants restaurant) {
		
		return null;
	}

	@Override
	public void removeRestaurant(Long restaurantId) {
		
	
	}

	@Override
	public List<MenuItems> getAllMenus() {
		
		return null;
	}

	@Override
	public List<Restaurants> getAllRestaurants() {
		
		return null;
	}

	@Override
	public List<Orders> getAllOrders() {
		
		return null;
	}

	@Override
	public Restaurants addCustomers(Customers customer) {
		return null;
	}

	@Override
	public Restaurants addMenuItem(MenuItems menuItem) {
		return null;
	}

	@Override
	public void removeCustomers(Long custId) {
		
	}

	@Override
	public void removeMenuItems(Long menuItemId) {
		
	}

}
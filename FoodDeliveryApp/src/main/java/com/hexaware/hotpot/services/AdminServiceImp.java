package com.hexaware.hotpot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.hotpot.dto.AdminDTO;
import com.hexaware.hotpot.dto.CustomersDTO;
import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.dto.RestaurantsDTO;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.entities.Restaurants;

@Service
public class AdminServiceImp implements IAdminService {

	
	
	@Override
	public String adminLogin(AdminDTO admindto) {
		
		return null;
	}

	@Override
	public Restaurants addRestaurant(RestaurantsDTO restaurant) {
		
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
	public Restaurants addCustomers(CustomersDTO customer) {
		return null;
	}

	@Override
	public Restaurants addMenuItem(MenuItemsDTO menuItem) {
		return null;
	}

	@Override
	public void removeCustomers(Long custId) {
		
	}

	@Override
	public void removeMenuItems(Long menuItemId) {
		
	}

}
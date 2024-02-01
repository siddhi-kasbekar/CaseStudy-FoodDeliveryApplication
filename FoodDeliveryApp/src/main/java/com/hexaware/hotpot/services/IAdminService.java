package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.entities.Administrator;
import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.entities.Restaurants;

public interface IAdminService {

	public String adminLogin(Administrator admin);

	public Restaurants addRestaurant(Restaurants restaurant);

	public void removeRestaurant(Long restaurantId);

	public List<MenuItems> getAllMenus();

	public List<Restaurants> getAllRestaurants();

	public List<Orders> getAllOrders();

	public Restaurants addCustomers(Customers customer);
	public Restaurants addMenuItem(MenuItems menuItem);
	
	public void removeCustomers(Long custId);
	public void removeMenuItems(Long menuItemId);
}

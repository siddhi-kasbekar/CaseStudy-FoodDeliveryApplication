package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.dto.AdminDTO;
import com.hexaware.hotpot.dto.CustomersDTO;
import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.dto.RestaurantsDTO;

import com.hexaware.hotpot.entities.Customers;

import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.entities.Restaurants;

public interface IAdminService {

	public String adminLogin(AdminDTO admindto);

	public Restaurants addRestaurant(RestaurantsDTO restaurant);

	public void removeRestaurant(Integer restaurantId);

	public List<MenuItems> getAllMenus();

	public List<Restaurants> getAllRestaurants();

	public List<Orders> getAllOrders();

	public Customers addCustomers(CustomersDTO customerDTO);
	public MenuItems addMenuItem(MenuItemsDTO menuItemDTO,int restaurantId);
	
	public void removeCustomers(Long customerId);
	public void removeMenuItems(Long menuItemId);
}

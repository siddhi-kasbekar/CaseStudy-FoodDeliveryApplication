package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.dto.AdminDTO;
import com.hexaware.hotpot.dto.CustomersDTO;
import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.dto.RestaurantsDTO;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.entities.Restaurants;

public interface IAdminService {

	public String adminLogin(AdminDTO admindto);

	public Restaurants addRestaurant(RestaurantsDTO restaurant);

	public void removeRestaurant(Long restaurantId);

	public List<MenuItems> getAllMenus();

	public List<Restaurants> getAllRestaurants();

	public List<Orders> getAllOrders();

	public Restaurants addCustomers(CustomersDTO customer);
	public Restaurants addMenuItem(MenuItemsDTO menuItem);
	
	public void removeCustomers(Long custId);
	public void removeMenuItems(Long menuItemId);
}

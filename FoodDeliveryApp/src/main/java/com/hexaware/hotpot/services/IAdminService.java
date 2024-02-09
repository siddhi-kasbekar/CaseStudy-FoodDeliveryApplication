package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.dto.AdminDTO;
import com.hexaware.hotpot.dto.DiscountDTO;
import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.dto.RestaurantsDTO;
import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.Discount;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.entities.Restaurants;

public interface IAdminService {

	public String adminLogin(AdminDTO admindto);

	public Restaurants addRestaurant(RestaurantsDTO restaurant);

	public void removeRestaurant(Integer restaurantId) ;

	public List<MenuItems> getAllMenus();

	public List<Restaurants> getAllRestaurants();

	public List<Orders> getAllOrders();
	
	public List<Customers> getAllCustomers();

	public MenuItems addMenuItem(MenuItemsDTO menuItemDTO,int restaurantId);
	
	public void removeMenuItems(Long menuItemId);
	
	public Discount addDiscount(DiscountDTO discountdto);
	
	public void removeDiscount(int discountId);
}

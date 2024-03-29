package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.dto.RestaurantsDTO;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Restaurants;
import com.hexaware.hotpot.exception.LocationNotFoundException;
import com.hexaware.hotpot.exception.MenuItemNotFoundException;
import com.hexaware.hotpot.exception.RestaurantNotFoundException;

public interface IRestaurantService {

	public Restaurants registerRestaurant(RestaurantsDTO restaurantDTO);

	public MenuItems addMenu(MenuItemsDTO menuDTO,int restaurantId);

	public void updateMenu(long menuItemId,MenuItemsDTO menuDTO) throws MenuItemNotFoundException;

	

	public List<MenuItems> getMenuByCategory(String category) throws MenuItemNotFoundException ;

	public List<Restaurants> searchRestaurants(String keyword)throws RestaurantNotFoundException;
	public List<Restaurants> searchByLocation(String location) throws LocationNotFoundException;
	
	List<Restaurants> getRestaurantsByAdminId( int adminId);
}

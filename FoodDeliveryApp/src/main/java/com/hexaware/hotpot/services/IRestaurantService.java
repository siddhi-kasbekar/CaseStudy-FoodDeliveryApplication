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

	public String loginRestaurant(RestaurantsDTO restaurantDTO);

	public void addMenu(MenuItemsDTO menuDTO,int restaurantId);

	public void updateMenu(MenuItemsDTO menuDTO);

	public void deleteMenu(int menuId);

	public List<MenuItems> getMenuByCategory(String category) throws MenuItemNotFoundException ;

	//public List<MenuItems> getOrdersByRestaurantId(Restaurants restaurant) throws RestaurantNotFoundException;
	public List<Restaurants> searchRestaurants(String keyword)throws RestaurantNotFoundException;
	public List<Restaurants> searchByLocation(String location) throws LocationNotFoundException;
}

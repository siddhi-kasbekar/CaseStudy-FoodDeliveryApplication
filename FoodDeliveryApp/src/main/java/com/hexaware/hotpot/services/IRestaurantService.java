package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.dto.RestaurantsDTO;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Restaurants;

public interface IRestaurantService {

	public Restaurants registerRestaurant(RestaurantsDTO restaurantDTO);

	public String loginRestaurant(RestaurantsDTO restaurantDTO);

	public MenuItems addMenu(MenuItemsDTO menuDTO,int restaurantId);

	public void updateMenu(MenuItemsDTO menuDTO);

	public void deleteMenu(int menuId);

	public List<MenuItems> getMenuByCategory(String category);

	public List<MenuItems> getOrdersByRestaurantId(Restaurants restaurant);
	public List<Restaurants> searchRestaurants(String keyword);
	public List<Restaurants> searchByLocation(String location);
}

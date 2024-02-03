package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.dto.RestaurantsDTO;
import com.hexaware.hotpot.entities.MenuItems;

public interface IRestaurantService {

	public int registerRestaurant(RestaurantsDTO restaurantDTO);

	public String loginRestaurant(RestaurantsDTO restaurantDTO);

	public void addMenu(MenuItemsDTO menuDTO);

	public void updateMenu(MenuItemsDTO menuDTO);

	public void deleteMenu(Long menuId);

	public List<MenuItems> getMenuByCategory(String category);

	public List<MenuItems> getOrdersByRestaurantId(Long restaurantId);
}

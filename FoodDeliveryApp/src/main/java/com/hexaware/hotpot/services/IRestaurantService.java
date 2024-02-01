package com.hexaware.hotpot.services;

import java.awt.Menu;
import java.util.List;

import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Restaurants;

public interface IRestaurantService {

	public Long registerRestaurant(Restaurants restaurant);

	public String loginRestaurant(Restaurants restaurant);

	public void addMenu(Menu menuDTO);

	public void updateMenu(MenuItems menu);

	public void deleteMenu(Long menuId);

	public List<MenuItems> getMenuByCategory(String category);

	public List<MenuItems> getOrdersByRestaurantId(Long restaurantId);
}

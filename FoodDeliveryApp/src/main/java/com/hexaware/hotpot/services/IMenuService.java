package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.exception.MenuItemNotFoundException;

public interface IMenuService {

	public List<MenuItems> getMenuByCategory(String category,int restaurantId) throws MenuItemNotFoundException;

	public List<MenuItems> searchMenuItemsByKeyword(String keyword,int restaurantId) throws MenuItemNotFoundException;
	
	public List<MenuItems> getByRestaurant(int restaurantId);
	public void deleteMenu(long menuId);
	
	public List<MenuItems> getMenuItemsByRestaurantId(int restaurantId, boolean showOnlyVegetarian);
}

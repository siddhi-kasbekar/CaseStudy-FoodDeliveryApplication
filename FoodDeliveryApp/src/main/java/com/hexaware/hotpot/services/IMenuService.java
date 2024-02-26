package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.exception.MenuItemNotFoundException;

public interface IMenuService {

	public List<MenuItems> getMenuByCategory(String category) throws MenuItemNotFoundException;

	public List<MenuItems> searchMenuItemsByKeyword(String keyword) throws MenuItemNotFoundException;
	
	public List<MenuItems> getByRestaurant(int restaurantId);
	public void deleteMenu(long menuId);
}

package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.entities.MenuItems;

public interface IMenuService {

	public List<MenuItems> getMenuByCategory(String category);

	public List<MenuItems> searchMenuItemsByKeyword(String keyword);
}

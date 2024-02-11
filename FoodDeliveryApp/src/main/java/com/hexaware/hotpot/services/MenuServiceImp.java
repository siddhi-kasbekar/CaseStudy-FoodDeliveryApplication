package com.hexaware.hotpot.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.exception.MenuItemNotFoundException;
import com.hexaware.hotpot.repository.MenuItemsRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MenuServiceImp implements IMenuService {

	@Autowired
	MenuItemsRepository repo;

	

	@Override
	public List<MenuItems> getMenuByCategory(String category) throws MenuItemNotFoundException {

		List<MenuItems> menuItem = repo.findByCategory(category);
		if (menuItem.isEmpty()) {
			throw new MenuItemNotFoundException("Error retrieving menu items for category: " + category);
		}
		
		return menuItem;
	}

	@Override
	public List<MenuItems> searchMenuItemsByKeyword(String keyword) throws MenuItemNotFoundException {
		List<MenuItems> menuItem = repo.findByItemNameContainingIgnoreCase(keyword);
		if (menuItem.isEmpty()) {
			throw new MenuItemNotFoundException("Error retrieving menu items for keyword: " + keyword);
		}
		
		return menuItem;
	}

}

package com.hexaware.hotpot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.exception.MenuItemNotFoundException;
import com.hexaware.hotpot.repository.MenuItemsRepository;

import jakarta.transaction.Transactional;

/*
 * Author name:NipurnaBandi
 * 
 * Class Description:contains business logic and functionalities related to MenuItems and also handles crud operations.
 * 
 */


@Service
@Transactional
public class MenuServiceImp implements IMenuService {

	@Autowired
	MenuItemsRepository repo;

	@Override
	public List<MenuItems> getMenuByCategory(String category) throws MenuItemNotFoundException {

		List<MenuItems> menuItem = repo.findByCategory(category);
		if (menuItem.isEmpty()) {
			throw new MenuItemNotFoundException(" menu items for category: " + category+" not found");
		}

		return menuItem;
	}

	@Override
	public List<MenuItems> searchMenuItemsByKeyword(String keyword) throws MenuItemNotFoundException {
		List<MenuItems> menuItem = repo.findByItemNameContainingIgnoreCase(keyword);
		if (menuItem.isEmpty()) {
			throw new MenuItemNotFoundException(" menu items for keyword: " + keyword+" not found");
		}

		return menuItem;
	}

	@Override
	public void deleteMenu(long menuId) {
		repo.deleteById(menuId);
		
	}

}

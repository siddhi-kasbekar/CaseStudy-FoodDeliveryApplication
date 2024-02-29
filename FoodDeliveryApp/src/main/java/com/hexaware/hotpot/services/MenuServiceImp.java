package com.hexaware.hotpot.services;

import java.util.List;
import java.util.stream.Collectors;

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
	public List<MenuItems> getMenuByCategory(String category,int restaurantId) throws MenuItemNotFoundException {

		List<MenuItems> menuItem = repo.findByCategoryAndRestaurantRestaurantId(category,restaurantId);
		if (menuItem.isEmpty()) {
			throw new MenuItemNotFoundException(" menu items for category: " + category+" not found");
		}

		return menuItem;
	}

	@Override
	public List<MenuItems> searchMenuItemsByKeyword(String keyword,int restaurantId) throws MenuItemNotFoundException {
		List<MenuItems> menuItem = repo.findByItemNameContainingIgnoreCaseAndRestaurantRestaurantId(keyword,restaurantId);
		if (menuItem.isEmpty()) {
			throw new MenuItemNotFoundException(" menu items for keyword: " + keyword+" not found");
		}

		return menuItem;
	}

	@Override
	public void deleteMenu(long menuId) {
		repo.deleteById(menuId);
		
	}

	@Override
	public List<MenuItems> getByRestaurant(int restaurantId) {
		// TODO Auto-generated method stub
		return repo.findByRestaurantRestaurantId(restaurantId);
	}
	
	public List<MenuItems> getMenuItemsByRestaurantId(int restaurantId, boolean showOnlyVegetarian) {
	    List<MenuItems> menuItems;
	    if (showOnlyVegetarian) {
	        menuItems = repo.findByRestaurantRestaurantIdAndSpecialDietaryInfo(restaurantId, "veg");
	    } else {
	        menuItems = repo.findByRestaurantRestaurantId(restaurantId);
	    }
	    return menuItems;
	}

}

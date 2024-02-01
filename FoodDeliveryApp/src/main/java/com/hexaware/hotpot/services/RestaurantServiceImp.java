package com.hexaware.hotpot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.dto.RestaurantsDTO;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.repository.RestaurantsRepository;

public class RestaurantServiceImp implements IRestaurantService {

	@Autowired
	RestaurantsRepository repo;
	
	@Override
	public Long registerRestaurant(RestaurantsDTO restaurantDTO) {
		
		return null;
	}

	@Override
	public String loginRestaurant(RestaurantsDTO restaurantDTO) {
		
		return null;
	}

	@Override
	public void addMenu(MenuItemsDTO menuDTO) {
		

	}

	@Override
	public void updateMenu(MenuItemsDTO menuDTO) {
		

	}

	@Override
	public void deleteMenu(Long menuId) {
		

	}

	@Override
	public List<MenuItems> getMenuByCategory(String category) {
		
		return null;
	}

	@Override
	public List<MenuItems> getOrdersByRestaurantId(Long restaurantId) {
		
		return null;
	}

}

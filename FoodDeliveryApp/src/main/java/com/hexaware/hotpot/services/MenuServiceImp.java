package com.hexaware.hotpot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.repository.MenuItemsRepository;

public class MenuServiceImp implements IMenuService {

	@Autowired
	MenuItemsRepository repo;
	
	@Override
	public List<MenuItems> getMenuByCategory(String category) {
		
		return null;
	}

	@Override
	public List<MenuItems> searchMenuItems(String keyword) {
		
		return null;
	}

}

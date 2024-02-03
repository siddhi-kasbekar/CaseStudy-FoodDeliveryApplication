package com.hexaware.hotpot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.repository.MenuItemsRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MenuServiceImp implements IMenuService {

	@Autowired
	MenuItemsRepository repo;
	
	@Override
	public List<MenuItems> getMenuByCategory(String category) {
		
		return  repo.findByCategory(category);
	}

	@Override
	public List<MenuItems> searchMenuItems(String keyword) {
		
		return repo.findByItemNameContainingIgnoreCase(keyword);
	}

}

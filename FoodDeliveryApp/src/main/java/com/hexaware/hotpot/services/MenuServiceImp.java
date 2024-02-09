package com.hexaware.hotpot.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger  logger =	LoggerFactory.getLogger(MenuServiceImp.class);
	
	@Override
	public List<MenuItems> getMenuByCategory(String category) {
		
		return  repo.findByCategory(category);
	}

	@Override
	public List<MenuItems> searchMenuItemsByKeyword(String keyword) {
		
		return repo.findByItemNameContainingIgnoreCase(keyword);
	}

}

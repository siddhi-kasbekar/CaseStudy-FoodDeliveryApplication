package com.hexaware.hotpot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.exception.MenuItemNotFoundException;
import com.hexaware.hotpot.services.IMenuService;

@RestController
@RequestMapping("/api/menuitem")
public class MenuItemsController {

	@Autowired
	IMenuService service;
	
	@GetMapping("/getByCategory/{category}")
    @PreAuthorize("hasAuthority('customer')")
	public List<MenuItems> getMenuByCategory(@PathVariable String category) throws MenuItemNotFoundException{
		return service.getMenuByCategory(category);
	}

	@GetMapping("/getByKeyword/{keyword}")
    @PreAuthorize("hasAuthority('customer')")
	public List<MenuItems> searchMenuItems(@PathVariable String keyword) throws MenuItemNotFoundException{
		return service.searchMenuItemsByKeyword(keyword);
	}
}

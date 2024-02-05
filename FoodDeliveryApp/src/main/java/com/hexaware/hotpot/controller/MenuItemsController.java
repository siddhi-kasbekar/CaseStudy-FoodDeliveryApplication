package com.hexaware.hotpot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.services.IMenuService;

@RestController
@RequestMapping("/menuitem")
public class MenuItemsController {

	@Autowired
	IMenuService service;
	
	@GetMapping("/getbycategory/{category}")
	public List<MenuItems> getMenuByCategory(@PathVariable String category){
		return service.getMenuByCategory(category);
	}

	@GetMapping("/getbykeyword/{keyword}")
	public List<MenuItems> searchMenuItems(@PathVariable String keyword){
		return service.searchMenuItemsByKeyword(keyword);
	}
}

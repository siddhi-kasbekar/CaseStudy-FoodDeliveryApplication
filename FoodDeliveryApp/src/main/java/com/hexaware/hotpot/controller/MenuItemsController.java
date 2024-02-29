package com.hexaware.hotpot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.exception.MenuItemNotFoundException;
import com.hexaware.hotpot.services.IMenuService;

@RestController
@RequestMapping("/api/v1/menuItem")
public class MenuItemsController {

	@Autowired
	IMenuService service;
	
	@GetMapping("/getByCategory/{restaurantId}/{category}")
    @PreAuthorize("hasAuthority('customer')")
	public List<MenuItems> getMenuByCategory(@PathVariable String category,@PathVariable int restaurantId) throws MenuItemNotFoundException{
		return service.getMenuByCategory(category, restaurantId);
	}

	@GetMapping("/getByKeyword/{restaurantId}/{keyword}")
    @PreAuthorize("hasAuthority('customer')")
	public List<MenuItems> searchMenuItems(@PathVariable String keyword,@PathVariable int restaurantId) throws MenuItemNotFoundException{
		return service.searchMenuItemsByKeyword(keyword,restaurantId);
	}
	
	@DeleteMapping("/deleteMenu/{menuId}")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('manager')")
	public String deleteMenu(@PathVariable long menuId) {
		service.deleteMenu(menuId);
		return "menu deleted successfully";
	}
	
	@GetMapping("/getByRestaurant/{restaurantId}")
    @PreAuthorize("hasAuthority('customer')")
	public List<MenuItems> getMenuItemsByRestaurantId(
		    @PathVariable int restaurantId,
		    @RequestParam(required = false, defaultValue = "false") boolean showOnlyVegetarian) {

		    return service.getMenuItemsByRestaurantId(restaurantId, showOnlyVegetarian);
		}
}

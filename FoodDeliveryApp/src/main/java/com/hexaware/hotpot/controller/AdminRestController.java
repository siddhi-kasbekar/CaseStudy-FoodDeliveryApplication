package com.hexaware.hotpot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.hotpot.dto.CustomersDTO;
import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.dto.RestaurantsDTO;
import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.entities.Restaurants;
import com.hexaware.hotpot.services.IAdminService;

@RestController
@RequestMapping("api/admin")
public class AdminRestController {

	@Autowired
	private IAdminService adminservice;

	@PostMapping("/add-restaurant")
	public String addRestaurant(@RequestBody RestaurantsDTO restaurantDTO) {
		Restaurants restaurant = adminservice.addRestaurant(restaurantDTO);
		if (restaurant != null) {
			return "Restaurant added successfully";
		} else {
			return "Failed to add restaurant";
		}
	}

	@DeleteMapping("/removeRestaurant/{restaurantId}")
	public String removeRestaurant(@PathVariable Integer restaurantId) {
		adminservice.removeRestaurant(restaurantId);
		return "Restaurant removed successfully";
	}

	@GetMapping("/getAllMenus")
	public List<MenuItems> getAllMenus() {
		return adminservice.getAllMenus();
	}

	@GetMapping("/getAllRestaurants")
	public List<Restaurants> getAllRestaurants() {
		return adminservice.getAllRestaurants();
	}

	@GetMapping("/getAllOrders")
	public List<Orders> getAllOrders() {
		return adminservice.getAllOrders();
	}
	
	@GetMapping("/getAllCustomers")
	public List<Customers> getAllCustomers() {
		return adminservice.getAllCustomers();
	}



	@PostMapping("/addMenuItem/{restaurantId}")
	public String addMenuItem(@RequestBody MenuItemsDTO menuItemDTO,@PathVariable int restaurantId) {
		
		MenuItems menuItem = adminservice.addMenuItem(menuItemDTO,restaurantId);
		if (menuItem != null) {
			return "Menu item added successfully";
		} else {
			return "Failed to add menu item";
		}

	}
	
	

    @DeleteMapping("/removeMenuItems/{menuItemId}")
    public String removeMenuItems(@PathVariable Long menuItemId) {
        adminservice.removeMenuItems(menuItemId);
        return "Menu item removed successfully";
    }
}

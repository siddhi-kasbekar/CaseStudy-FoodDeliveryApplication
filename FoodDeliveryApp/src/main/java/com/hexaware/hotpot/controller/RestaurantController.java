package com.hexaware.hotpot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.dto.RestaurantsDTO;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Restaurants;
import com.hexaware.hotpot.services.IRestaurantService;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

	@Autowired
	IRestaurantService service ;
	
	@PostMapping("/register")
	public String registerRestaurant(@RequestBody RestaurantsDTO restaurantDTO) {
		 service.registerRestaurant(restaurantDTO);
		 return "restaurant registerd successfully";
	}

//	public String loginRestaurant(RestaurantsDTO restaurantDTO) {
//		return service.loginRestaurant(restaurantDTO);
//	}

	@PostMapping("/add/{restaurantId}")
	public String addMenu(@RequestBody MenuItemsDTO menuDTO,@PathVariable int restaurantId) {
		service.addMenu(menuDTO,restaurantId);
		return "menu added successfully";
	}

	@PutMapping("/update")
	public String updateMenu(@RequestBody MenuItemsDTO menuDTO) {
		service.updateMenu(menuDTO);
		return "menu updated successfully";
	}

	@DeleteMapping("/delete/{menuId}")
	public String deleteMenu(@PathVariable int menuId) {
		service.deleteMenu(menuId);
		return "menu deleted successfully";
	}

	@GetMapping("/getmenu/{category}")
	public List<MenuItems> getMenuByCategory(@PathVariable String category){
		return service.getMenuByCategory(category);
	}

	@GetMapping("/getorder/{restaurantId}")
	public List<MenuItems> getOrdersByRestaurantId( @PathVariable Restaurants restaurant){
		return service.getOrdersByRestaurantId(restaurant);
	}
	
	@GetMapping("/getrestaurant/{keyword}")
	public List<Restaurants> searchRestaurants(@PathVariable String keyword){
		return service.searchRestaurants(keyword);
	}
}

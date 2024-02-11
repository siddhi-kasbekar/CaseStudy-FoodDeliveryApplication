package com.hexaware.hotpot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.hexaware.hotpot.entities.Restaurants;
import com.hexaware.hotpot.exception.LocationNotFoundException;
import com.hexaware.hotpot.exception.RestaurantNotFoundException;
import com.hexaware.hotpot.services.IRestaurantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

	@Autowired
	IRestaurantService service ;
	
	@PostMapping("/register")
    @PreAuthorize("hasAuthority('admin')")
	public String registerRestaurant(@RequestBody RestaurantsDTO restaurantDTO) {
		 service.registerRestaurant(restaurantDTO);
		 return "restaurant registerd successfully";
	}

//	public String loginRestaurant(RestaurantsDTO restaurantDTO) {
//		return service.loginRestaurant(restaurantDTO);
//	}

	@PostMapping("/addMenu/{restaurantId}")
    @PreAuthorize("hasAuthority('admin')")
	public String addMenu(@Valid @RequestBody MenuItemsDTO menuDTO,@PathVariable int restaurantId) {
		service.addMenu(menuDTO,restaurantId);
		return "menu added successfully";
	}

	@PutMapping("/updateRestaurant")
    @PreAuthorize("hasAuthority('admin')")
	public String updateMenu(@RequestBody MenuItemsDTO menuDTO) {
		service.updateMenu(menuDTO);
		return "menu updated successfully";
	}

	@DeleteMapping("/deleteRestaurant/{menuId}")
    @PreAuthorize("hasAuthority('admin')")
	public String deleteMenu(@PathVariable int menuId) {
		service.deleteMenu(menuId);
		return "menu deleted successfully";
	}

//	@GetMapping("/getMenu/{category}")
//	
//	public List<MenuItems> getMenuByCategory(@PathVariable String category) throws MenuItemNotFoundException{
//		return service.getMenuByCategory(category);
//	}

//	@GetMapping("/getOrder/{restaurantId}")
//	public List<MenuItems> getOrdersByRestaurantId( @PathVariable Restaurants restaurant) throws RestaurantNotFoundException{
//		return service.getOrdersByRestaurantId(restaurant);
//	}
	
	@GetMapping("/getRestaurant/{keyword}")
    @PreAuthorize("hasAuthority('customer')")
	public List<Restaurants> searchRestaurants(@PathVariable String keyword) throws RestaurantNotFoundException{
		return service.searchRestaurants(keyword);
	}
	
	@GetMapping("/searchByLocation/{location}")
    @PreAuthorize("hasAuthority('customer')")
	public List<Restaurants> searchByLocation(@PathVariable String location) throws LocationNotFoundException{
		
		return service.searchByLocation(location);
		
	}
}

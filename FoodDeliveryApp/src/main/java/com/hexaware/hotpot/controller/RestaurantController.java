package com.hexaware.hotpot.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.dto.RestaurantsDTO;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Restaurants;
import com.hexaware.hotpot.exception.LocationNotFoundException;
import com.hexaware.hotpot.exception.MenuItemNotFoundException;
import com.hexaware.hotpot.exception.RestaurantNotFoundException;
import com.hexaware.hotpot.services.IRestaurantService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {

	@Autowired
	IRestaurantService service ;
	
	@PostMapping("/register")
    @PreAuthorize("hasAuthority('admin')")
	public Restaurants registerRestaurant(@RequestBody RestaurantsDTO restaurantDTO) {
		 return service.registerRestaurant(restaurantDTO);

	}
	
	
	
	@PostMapping("/addMenu")
    @PreAuthorize("hasAuthority('manager')")
	public String addMenu(
		    @RequestPart("menuDTOJson") String menuDTOJson,
		    @RequestPart("imageFile") MultipartFile imageFile		   ) {
		    
		    ObjectMapper objectMapper = new ObjectMapper();
		    try {
		        MenuItemsDTO menuItemDTO = objectMapper.readValue(menuDTOJson, MenuItemsDTO.class);

		        menuItemDTO.setImageFile(imageFile);

		        service.addMenu(menuItemDTO, menuItemDTO.getRestaurantId());
		        return "menu added successfully";
		    } catch (IOException e) {
		       
		        return "Error parsing menuDTOJson: " + e.getMessage();
		    }
		}

	@PutMapping("/updateMenu/{menuItemId}")
    @PreAuthorize("hasAuthority('manager')")
	public String updateMenu(@PathVariable long menuItemId,@RequestBody MenuItemsDTO menuDTO) throws MenuItemNotFoundException {
		service.updateMenu(menuItemId,menuDTO);
		return "menu updated successfully";
	}

	

	@GetMapping("/getMenu/{category}")
	@PreAuthorize("hasAuthority('customer')")
	public List<MenuItems> getMenuByCategory(@PathVariable String category) throws MenuItemNotFoundException{
		return service.getMenuByCategory(category);
	}


	
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

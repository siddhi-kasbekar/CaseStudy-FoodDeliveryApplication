package com.hexaware.hotpot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.hotpot.dto.AuthRequest;
import com.hexaware.hotpot.dto.DiscountDTO;
import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.dto.RestaurantsDTO;
import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.Discount;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.entities.Restaurants;
import com.hexaware.hotpot.services.IAdminService;
import com.hexaware.hotpot.services.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/admin")
public class AdminRestController {
	
    private static final Logger log = LoggerFactory.getLogger(AdminRestController.class);


	@Autowired
	private IAdminService adminservice;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/login/authenticate")
	
	 public String  authenticateAndGetTokent(@RequestBody  AuthRequest authRequest) {
		 
		 
		 
		Authentication authentication = 	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		 
		String token = null;
		
				if(authentication.isAuthenticated()) {
					
				  // call generate token method from jwtService class
					
			token =		jwtService.generateToken(authRequest.getUsername());
					
			log.info("Tokent : "+token);
					
				}
				else {
					
					log.info("invalid");
					
					 throw new UsernameNotFoundException("UserName or Password in Invalid / Invalid Request");
					
				}
		
				return token;
		 
	 }

	@PostMapping("/add-restaurant")
    @PreAuthorize("hasAuthority('admin')")
	public String addRestaurant(@RequestBody @Valid RestaurantsDTO restaurantDTO) {
		Restaurants restaurant = adminservice.addRestaurant(restaurantDTO);
		if (restaurant != null) {
			return "Restaurant added successfully";
		} else {
			return "Failed to add restaurant";
		}
	}

	@DeleteMapping("/removeRestaurant/{restaurantId}")
    @PreAuthorize("hasAuthority('admin')")

	public String removeRestaurant(@PathVariable Integer restaurantId) {
		adminservice.removeRestaurant(restaurantId);
		return "Restaurant removed successfully";
	}

	@GetMapping("/getAllMenus")
    @PreAuthorize("hasAuthority('admin')")

	public List<MenuItems> getAllMenus() {
		return adminservice.getAllMenus();
	}

	@GetMapping("/getAllRestaurants")
    @PreAuthorize("hasAuthority('admin')")
	public List<Restaurants> getAllRestaurants() {
		return adminservice.getAllRestaurants();
	}

	@GetMapping("/getAllOrders")
    @PreAuthorize("hasAuthority('admin')")
	public List<Orders> getAllOrders() {
		return adminservice.getAllOrders();
	}
	
	@GetMapping("/getAllCustomers")
    @PreAuthorize("hasAuthority('admin')")
	public List<Customers> getAllCustomers() {
		return adminservice.getAllCustomers();
	}



	@PostMapping("/addMenuItem/{restaurantId}")
    @PreAuthorize("hasAuthority('admin')")
	public String addMenuItem(@RequestBody @Valid MenuItemsDTO menuItemDTO,@PathVariable int restaurantId) {
		
		MenuItems menuItem = adminservice.addMenuItem(menuItemDTO,restaurantId);
		if (menuItem != null) {
			return "Menu item added successfully";
		} else {
			return "Failed to add menu item";
		}

	}
	

    @DeleteMapping("/removeMenuItems/{menuItemId}")
    @PreAuthorize("hasAuthority('admin')")
    public String removeMenuItems(@PathVariable Long menuItemId) {
        adminservice.removeMenuItems(menuItemId);
        return "Menu item removed successfully";
    }
    
    @PostMapping("/add-discount")
    @PreAuthorize("hasAuthority('admin')")
	public String addDiscount(@RequestBody @Valid DiscountDTO discountDTO) {
		Discount discount = adminservice.addDiscount(discountDTO);
		if (discount != null) {
			return "Discount added successfully";
		} else {
			return "Discount to add restaurant";
		}
	}
    
    @DeleteMapping("/removeDiscount/{discountId}")
    @PreAuthorize("hasAuthority('admin')")
    public String removeDiscount(@PathVariable int discountId) {
        adminservice.removeDiscount(discountId);
        return "Discount removed successfully";
    }
}

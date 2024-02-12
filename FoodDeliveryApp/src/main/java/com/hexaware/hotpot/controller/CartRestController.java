package com.hexaware.hotpot.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.entities.Cart;
import com.hexaware.hotpot.exception.CustomerNotFoundException;
import com.hexaware.hotpot.services.ICartService;

@RestController
@RequestMapping("api/v1/cart")
public class CartRestController {
	
    


	@Autowired
	ICartService cartService;
	
	
	@PostMapping("/save-cart/{customerId}")
	@PreAuthorize("hasAuthority('customer')")
	public Cart saveCartState(@PathVariable long customerId, @RequestParam double total, @RequestBody Map<String, Object> requestBody) throws CustomerNotFoundException {
	    List<MenuItemsDTO> menuItems = parseMenuItems(requestBody);

		return cartService.saveCartState(customerId, menuItems, total);
	}
	private List<MenuItemsDTO> parseMenuItems(Map<String, Object> requestBody) {
	    List<Map<String, Object>> menuItemList = (List<Map<String, Object>>) requestBody.get("menuItems");
	    List<MenuItemsDTO> menuItems = new ArrayList<>();
	    for (Map<String, Object> menuItemData : menuItemList) {
	        MenuItemsDTO menuItemDTO = new MenuItemsDTO();
	        menuItemDTO.setMenuItemId(Long.parseLong(menuItemData.get("menuItemId").toString()));
	        menuItemDTO.setQuantity(Integer.parseInt(menuItemData.get("quantity").toString()));
	        menuItems.add(menuItemDTO);
	    }
	    return menuItems;
	}
	
	

//	@PostMapping("/add/{customerId}")
//	public String addToCart(@PathVariable long customerId, @RequestBody Map<String, Object> payload) {
//
//		long menuItemId = Long.parseLong(payload.get("menuItemId").toString());
//		int quantity = Integer.parseInt(payload.get("quantity").toString());
//
//		cartService.addToCart(customerId, menuItemId, quantity);
//
//		return "added to cart ";
//
//	}
//	
//	@DeleteMapping("/remove/{customerId}")
//	public String removeFromCart(@PathVariable long customerId, @RequestParam long menuItemId) {
//	    cartService.removeFromCart(customerId, menuItemId);
//	    return "Item removed from the cart";
//	}

	

}

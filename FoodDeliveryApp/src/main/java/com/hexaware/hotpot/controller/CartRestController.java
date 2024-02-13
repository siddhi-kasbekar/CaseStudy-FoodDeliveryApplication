package com.hexaware.hotpot.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public Cart saveCartState(@PathVariable long customerId,  @RequestBody Map<String, Object> requestBody) throws CustomerNotFoundException {
	    List<MenuItemsDTO> menuItems = parseMenuItems(requestBody);

	    double total = (double) requestBody.get("total");

		return cartService.saveCartState(customerId, menuItems, total);
	}
	
	
@DeleteMapping("/clearAll/{customerId}")
@PreAuthorize("hasAuthority('customer')")
public String clearCart( @PathVariable  long customerId) throws CustomerNotFoundException{
	cartService.clearCart(customerId);
	return "cart cleared";
	
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


}

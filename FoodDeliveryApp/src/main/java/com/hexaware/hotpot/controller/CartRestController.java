package com.hexaware.hotpot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.hotpot.entities.Cart;
import com.hexaware.hotpot.exception.CustomerNotFoundException;
import com.hexaware.hotpot.services.ICartService;

@RestController
@RequestMapping("api/cart")
public class CartRestController {
	
    


	@Autowired
	ICartService cartService;
	
	
	@PostMapping("/save-cart/{customerId}/{cartId}")
    @PreAuthorize("hasAuthority('customer')")
	public Cart saveCartState(@RequestBody Cart cart,@PathVariable long customerId,@PathVariable int cartId) throws CustomerNotFoundException {
		
		return cartService.saveCartState(cart,customerId,cartId);
		
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

package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.dto.CartDetailsDTO;
import com.hexaware.hotpot.dto.DiscountDTO;
import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.entities.Cart;
import com.hexaware.hotpot.exception.CustomerNotFoundException;

public interface ICartService {


	
	public Cart saveCartState(long customerId,  List<MenuItemsDTO> menuItemsDTO,double total) throws CustomerNotFoundException ;


	
	public void clearCart(long customerId) throws CustomerNotFoundException;

	public Cart getCartItems(Long customerId);
	
	public void addToCart(Long customerId, int cartId, CartDetailsDTO cartDetailsDTO);
	
	public void removeFromCart(Long customerId, int cartId, CartDetailsDTO cartDetailsDTO);
	
	public void calculateDiscountedTotal(int cartId, DiscountDTO discount);
	
	
}

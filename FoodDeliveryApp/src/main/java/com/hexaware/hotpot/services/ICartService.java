package com.hexaware.hotpot.services;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.hotpot.dto.CartDetailsDTO;
import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.entities.Cart;
import com.hexaware.hotpot.exception.CustomerNotFoundException;
import com.hexaware.hotpot.exception.DiscountNotFoundException;

public interface ICartService {


	
	public Cart saveCartState(long customerId,  List<MenuItemsDTO> menuItemsDTO,double total) throws CustomerNotFoundException ;


	
	public void clearCart(long customerId) throws CustomerNotFoundException;

	public Cart getCartItems(Long customerId);
	
	public void addToCart(Long customerId, CartDetailsDTO cartDetailsDTO)throws CustomerNotFoundException;
	

	
	public void removeFromCart(Long customerId, Long menuItemId, int quantity);
	
	public void calculateDiscountedTotal(Long customerId, LocalDate currentDate) throws DiscountNotFoundException;
	
	public List<Object[]> getCartDetails(long customerId);
	
	
}

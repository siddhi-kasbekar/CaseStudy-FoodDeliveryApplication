package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.dto.CartDetailsDTO;
import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.entities.Cart;
import com.hexaware.hotpot.exception.CustomerNotFoundException;

public interface ICartService {


	
	public Cart saveCartState(long customerId,  List<MenuItemsDTO> menuItemsDTO,double total) throws CustomerNotFoundException ;


	


	public Cart getCartItems(Long customerId);
}

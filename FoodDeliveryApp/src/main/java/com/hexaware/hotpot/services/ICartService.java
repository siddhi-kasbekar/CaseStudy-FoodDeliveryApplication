package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.dto.CartDTO;
import com.hexaware.hotpot.entities.Cart;
import com.hexaware.hotpot.exception.CustomerNotFoundException;

public interface ICartService {


	
	public Cart saveCartState(Cart cart,long customerId,int cartId) throws CustomerNotFoundException ;


	


	public List<Cart> getCartItems(Long customerId);
}

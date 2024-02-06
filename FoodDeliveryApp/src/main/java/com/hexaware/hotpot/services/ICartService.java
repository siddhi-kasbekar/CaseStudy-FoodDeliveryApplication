package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.entities.Cart;

public interface ICartService {

	public void addToCart(long customerId, long menuId, int quantity);

	public void removeFromCart(long customerId, long menuId);


	public List<Cart> getCartItems(Long customerId);
}

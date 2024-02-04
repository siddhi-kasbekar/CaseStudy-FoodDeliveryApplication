package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.entities.Cart;

public interface ICartService {

	public int addToCart(Long customerId, Long menuId, int quantity);

	public void removeFromCart(long customerId, long menuId);

	public void updateCartQuantity(Long customerId, Long menuId, int newQuantity);

	public List<Cart> getCartItems(Long customerId);
}

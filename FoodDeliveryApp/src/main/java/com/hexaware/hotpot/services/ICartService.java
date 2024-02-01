package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.entities.Cart;

public interface ICartService {

	public Long addToCart(Long customerId, Long menuId, int quantity);

	public void removeFromCart(Long customerId, Long menuId);

	public void updateCartQuantity(Long customerId, Long menuId, int newQuantity);

	public List<Cart> getCartItems(Long userId);
}

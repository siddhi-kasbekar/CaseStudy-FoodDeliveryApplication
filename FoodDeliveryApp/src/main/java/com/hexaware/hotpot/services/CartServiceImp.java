package com.hexaware.hotpot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.entities.Cart;
import com.hexaware.hotpot.entities.CartDetails;

import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.exception.CustomerNotFoundException;
import com.hexaware.hotpot.repository.CartDetailsRepository;
import com.hexaware.hotpot.repository.CartRepository;
import com.hexaware.hotpot.repository.CustomersRepository;
import com.hexaware.hotpot.repository.MenuItemsRepository;

import jakarta.transaction.Transactional;

/*
 * Author name:Siddhi kasbekar
 * 
 * Class Description:contains business logic and functionalities related to cart and also handles crud operations.
 * 
 */

@Service
@Transactional
public class CartServiceImp implements ICartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CustomersRepository customerRepo;

	@Autowired
	private CartDetailsRepository cartDetailsRepo;

	@Autowired
	private MenuItemsRepository menuItemsRepo;

	@Override
	public Cart getCartItems(Long customerId) {

		return cartRepository.findByCustomerId(customerId);
	}

	@Override
	public Cart saveCartState(long customerId, List<MenuItemsDTO> menuItemsDTO, double total)
			throws CustomerNotFoundException {
		// Retrieve customer by ID
//	     customerRepo.findById(customerId)
//	            .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));

		// Check if a cart already exists for the customer
		Cart existingCart = cartRepository.findByCustomerId(customerId);
		Cart cart;
		if (existingCart != null) {
			// If a cart already exists, update the total
			existingCart.setTotal(total);
			// Update other properties if needed
			return cartRepository.save(existingCart);
		} else {
			// Create a new cart
			cart = new Cart();
			cart.setTotal(total); // Set the total
			cart.setCustomerId(customerId);

			// Save the new cart
			cartRepository.save(cart);
		}

		// Save cart details
		for (MenuItemsDTO menuItemDTO : menuItemsDTO) {
			// Retrieve or create menu item entity
			MenuItems menuItem = menuItemsRepo.findById(menuItemDTO.getMenuItemId())
					.orElseThrow(() -> new RuntimeException("Menu item not found"));

			CartDetails cartDetails = new CartDetails();
			cartDetails.setCart(cart);
			cartDetails.setMenuItems(menuItem);
			cartDetails.setQuantity(menuItemDTO.getQuantity());

			// Save the cart detail
			cartDetailsRepo.save(cartDetails);
		}

		return cart;
	}
	
	
	@Override
	@Transactional
	public void clearCart(long customerId) throws CustomerNotFoundException {
	    // Retrieve the cart by customer ID
	    Cart cart = cartRepository.findByCustomerId(customerId);
	    if (cart != null) {
	        // Set total to 0
	        cart.setTotal(0.0);
	        // Save the updated cart
	        cartRepository.save(cart);
	        
	     // Delete the cart details associated with the customer's cart
	        List<CartDetails> cartDetailsList = cartDetailsRepo.findByCart_CartId(cart.getCartId());
	        cartDetailsRepo.deleteAll(cartDetailsList);
	    } else {
	        throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
	    }
	}


}

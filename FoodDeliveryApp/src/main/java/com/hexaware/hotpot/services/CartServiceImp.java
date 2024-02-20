package com.hexaware.hotpot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.hotpot.dto.CartDetailsDTO;
import com.hexaware.hotpot.dto.DiscountDTO;
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

	@Override
	public void addToCart(Long customerId, int cartId, CartDetailsDTO cartDetailsDTO) {
	    // Check if the cart exists for the given customer
	    Optional<Cart> optionalCart = cartRepository.findById(cartId);

	    if (optionalCart.isPresent()) {
	        // If the cart exists, update the total
	        Cart cart = optionalCart.get();
	        cart.setTotal(0);
	        // Check if the item already exists in the cart
	        Optional<CartDetails> existingCartItem = cartDetailsRepo.findByCart_CartIdAndMenuItem_MenuItemId(cartId, cartDetailsDTO.getMenuItemId());

	        if (existingCartItem.isPresent()) {
	            // If the item already exists, increment the quantity by 1
	            CartDetails cartDetails = existingCartItem.get();
	            cartDetails.setQuantity(cartDetails.getQuantity() + 1);
	            // Update the existing CartDetails
	            cartDetailsRepo.save(cartDetails);
	        } else {
	            // If the item does not exist, create a new CartDetails
	            CartDetails cartDetails = createNewCartDetailsWithDTO(cart, cartDetailsDTO);
	            // Save the new CartDetails
	            cartDetailsRepo.save(cartDetails);
	        }

	        // Calculate and set the original total
	        cart.setTotal(calculateOriginTotal(cart));

	        // Save changes
	        cartRepository.save(cart);
	    } else {
	        // If the cart is not found, you might want to handle this scenario
	        throw new IllegalArgumentException("Cart with ID " + cartId + " not found");
	    }
	}



	
	
	private CartDetails createNewCartDetailsWithDTO(Cart cart, CartDetailsDTO cartDetailsDTO) {
	    // Fetch menu item based on menuItemId
		System.out.println(cartDetailsDTO.getMenuItemId());
	    MenuItems menuItem = menuItemsRepo.findById(cartDetailsDTO.getMenuItemId())
	            .orElseThrow(() -> new IllegalArgumentException("Menu item not found"));
	    

	    // Create a new CartDetails
	    CartDetails cartDetails = new CartDetails();
	    cartDetails.setCart(cart);
	    cartDetails.setMenuItems(menuItem);
	    cartDetails.setQuantity(cartDetailsDTO.getQuantity());
	    cartDetails.setPrice(cartDetailsDTO.getPrice()); 
	    return cartDetails;
	}

	private double calculateOriginTotal(Cart cart) {
	    // Fetch cart details for the given cart
	    List<CartDetails> cartDetailsList = cartDetailsRepo.findByCart_CartId(cart.getCartId());

	    double oTotal = cartDetailsList.stream()
	            .mapToDouble(cartDetails -> cartDetails.getPrice() * cartDetails.getQuantity())
	            .sum();

	    return oTotal;
	}

	
	@Override
	@Transactional
	public void removeFromCart(Long customerId, int cartId, CartDetailsDTO cartDetailsDTO) {
	    // Check if the cart exists for the given customer
	    Optional<Cart> optionalCart = cartRepository.findById(cartId);

	    if (optionalCart.isPresent()) {
	        // If the cart exists, update the total
	        Cart cart = optionalCart.get();
	        cart.setTotal(0);

	        // Check if the item already exists in the cart
	        Optional<CartDetails> existingCartItem = cartDetailsRepo.findByCart_CartIdAndMenuItem_MenuItemId(cartId, cartDetailsDTO.getMenuItemId());

	        if (existingCartItem.isPresent()) {
	            // If the item exists, decrement the quantity by 1
	            CartDetails cartDetails = existingCartItem.get();
	            int newQuantity = cartDetails.getQuantity() - 1;

	            if (newQuantity > 0) {
	                // If quantity is greater than 0, update the quantity
	                cartDetails.setQuantity(newQuantity);
	                cartDetailsRepo.save(cartDetails);
	            } else {
	                // If quantity becomes 0, delete the record from cart_details
	                cartDetailsRepo.delete(cartDetails);
	            }

	            // Calculate and set the original total
	            cart.setTotal(calculateOriginTotal(cart));

	            // Save changes
	            cartRepository.save(cart);
	        } else {
	            // If the item does not exist, you might want to handle this scenario
	            throw new IllegalArgumentException("Item not found in the cart");
	        }
	    } else {
	        // If the cart is not found, you might want to handle this scenario
	        throw new IllegalArgumentException("Cart with ID " + cartId + " not found");
	    }
	}

	
	
	@Override
	@Transactional
	public void calculateDiscountedTotal(int cartId, DiscountDTO discount) {
	    Optional<Cart> optionalCart = cartRepository.findById(cartId);
	    
	    if (optionalCart.isPresent() && discount != null) {
	        Cart cart = optionalCart.get();
	        int discountPercentage = discount.getDiscountPercentage();
	        double total = cart.getTotal();
	        double discountedTotal = total - (total * discountPercentage / 100);
	        cart.setTotal(discountedTotal);
	        cartRepository.save(cart);
	    }
	}
	
	


}

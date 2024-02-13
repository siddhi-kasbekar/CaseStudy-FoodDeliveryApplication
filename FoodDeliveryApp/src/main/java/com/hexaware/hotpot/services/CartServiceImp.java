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


//	@Override
//	public void addToCart(long customerId, long menuItemId, int quantity) {
//        Customers customer = customerRepo.findById(customerId)
//                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + customerId));
//
//        MenuItems menuItem = menuItemsRepo.findById(menuItemId)
//                .orElseThrow(() -> new EntityNotFoundException("Menu item not found with id: " + menuItemId));
//
//        Cart cart = customer.getCart();
//
//        CartMenuItems existingCartItem = findCartItemByMenuItem(cart, menuItem);
//
//        if (existingCartItem != null) {
//            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
//        } else {
//            CartMenuItems cartItem = new CartMenuItems(cart, menuItem, quantity);
//            cart.getCartMenuItems().add(cartItem);
//        }
//
//        updateCartTotal(cart);
//
//        cartRepository.save(cart);
//
//        cartMenuItemsRepo.saveAll(cart.getCartMenuItems());
//    }
//
//    private CartMenuItems findCartItemByMenuItem(Cart cart, MenuItems menuItem) {
//        for (CartMenuItems cartItem : cart.getCartMenuItems()) {
//            if (cartItem.getMenuItems().equals(menuItem)) {
//                return cartItem;
//            }
//        }
//        return null;
//    }
//
//    private void updateCartTotal(Cart cart) {
//        double total = 0.0;
//
//        // Calculate total price without any discounts
//        for (CartMenuItems cartItem : cart.getCartMenuItems()) {
//            total += cartItem.getMenuItems().getPrice() * cartItem.getQuantity();
//        }
//
//        // Check if total exceeds 2000
//        if (total > 1000) {
//            // Fetch applicable discount
//        	Optional<Discount> optionalDiscount = discountRepo.findFirstByOrderByStartDateDesc(); 
//        	Discount discount = optionalDiscount.orElseThrow(); // or handle the absence of discount differently if needed            
//            // Apply discount if found
//            if (discount != null) {
//                double discountPercentage = discount.getDiscountPercentage().doubleValue();
//                double discountAmount = total * (discountPercentage / 100.0);
//                total -= discountAmount;
//            }
//        }
//
//        cart.setTotal(total);
//    }
//
//
//    
//    
//    @Transactional
//    @Override
//    public void removeFromCart(long customerId, long menuId) {
//        Customers customer = customerRepo.findById(customerId)
//                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + customerId));
//
//        MenuItems menuItem = menuItemsRepo.findById(menuId)
//                .orElseThrow(() -> new EntityNotFoundException("Menu item not found with id: " + menuId));
//
//        Cart cart = customer.getCart();
//
//        CartMenuItems existingCartItem = findCartItemByMenuItem(cart, menuItem);
//
//        if (existingCartItem != null) {
//            double removedItemTotal = existingCartItem.getMenuItems().getPrice() * existingCartItem.getQuantity();
//            
//            if (existingCartItem.getQuantity() > 0) {
//                existingCartItem.setQuantity(0); // Decrease the quantity by 1
//                cartMenuItemsRepo.deleteById(existingCartItem.getId());
//
//            } else {
//                cartMenuItemsRepo.deleteById(existingCartItem.getId());
//      }
//            
//            updateCartTotalAfterRemoval(cart, removedItemTotal); // Update the total amount for the cart by subtracting the removed item's total
//            cartRepository.save(cart);
//            cartMenuItemsRepo.save(existingCartItem); // Save the updated cart item
//        } else {
//            throw new EntityNotFoundException("Menu item with id " + menuId + " not found in the cart.");
//        }
//    }
//    
//    
//    
//
//	private void updateCartTotalAfterRemoval(Cart cart, double removedItemTotal) {
//	    double total = cart.getTotal() - removedItemTotal;
//	    if (total > 1000) { // Check if the new total exceeds 1000 rupees
//	        // Apply discount if the new total exceeds 1000 rupees
//	        total = applyDiscount(total);
//	    }
//	    cart.setTotal(total);
//	}
//	
//	private double applyDiscount(double total) {
//	    // Fetch the latest discount from the database
//	    Optional<Discount> latestDiscountOptional = discountRepo.findFirstByOrderByStartDateDesc();
//	    
//	    if (latestDiscountOptional.isPresent()) {
//	        Discount latestDiscount = latestDiscountOptional.get();
//	        double discountPercentage = latestDiscount.getDiscountPercentage().doubleValue();
//	        logger.info("Latest discount fetched: {}", latestDiscount);
//
//	        // Apply discount logic
//	        double discountAmount = total * (discountPercentage / 100.0);
//	        
//	        logger.info("Discount Percentage: {}", discountPercentage);
//	        logger.info("Discount Amount: {}", discountAmount);
//	    
//	        total -= discountAmount;
//	    }
//	    
//	    return total;
//	}

}

package com.hexaware.hotpot.services;


import java.util.List;

import java.util.Optional;
import java.util.Set;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.hotpot.entities.Cart;
import com.hexaware.hotpot.entities.CartMenuItems;
import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.MenuItems;

import com.hexaware.hotpot.exception.CustomerNotFoundException;
import com.hexaware.hotpot.repository.CartMenuitemsRepository;

import com.hexaware.hotpot.repository.CartRepository;
import com.hexaware.hotpot.repository.CustomersRepository;
import com.hexaware.hotpot.repository.MenuItemsRepository;

import jakarta.persistence.EntityNotFoundException;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class CartServiceImp implements ICartService {
	
	private static final Logger logger = LoggerFactory.getLogger(CartServiceImp.class);

	
	@Autowired
    private CartRepository cartRepository;
	
	@Autowired
	private CustomersRepository customerRepo;	
	
	
	@Autowired
    private CartMenuitemsRepository cartMenuItemsRepo;
	
	@Autowired
    private MenuItemsRepository menuItemsRepo;
	
	


	
	@Autowired
    private MenuItemsRepository menuItemsRepo;
	
	@Autowired
    private CartMenuItemsRepository cartMenuItemsRepo

	

	



	

   
	@Override
	public List<Cart> getCartItems(Long customerId) {
		
		return cartRepository.findByCustomerCustomerId(customerId);
	}


	@Override
	public Cart saveCartState(Cart cart,long customerId,int cartId) throws CustomerNotFoundException {
	    
	    Customers customer = customerRepo.findById(customerId)
	            .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));

	    // Set the customer for the cart
	    cart.setCustomer(customer);

	    // Clear existing cart items before adding new ones
	    cart.getCartMenuItems().clear();

	    // Check if the cart already exists in the database
	    Optional<Cart> existingCartOptional = cartRepository.findById(cartId);
	    if (existingCartOptional.isPresent()) {
	        // If the cart exists, update its attributes
	        Cart existingCart = existingCartOptional.get();
	        existingCart.setTotal(cart.getTotal());
	        // Set any other attributes if needed

	        // Save the updated cart
	        cartRepository.save(existingCart);
	        cart = existingCart; // Update the reference to the existing cart
	    } else {
	    	if (cart.getTotal() == 0) {
	            // Set a default total if it's not already set
	            cart.setTotal(0.0);
	        }
	        cart = cartRepository.save(cart);
	    }

	    // Log cart state
	    logger.info("Cart state saved successfully for customer with ID: {}", customerId);
	    logger.info("Total: {}", cart.getTotal());

	    // Save cart items from the cart
	    Set<CartMenuItems> cartItems = cart.getCartMenuItems();
	    for (CartMenuItems cartItem : cartItems) {
	        // Set the cart for each cart item
	        cartItem.setCart(cart);

	        // Retrieve or create menu item entity
	        MenuItems menuItem = cartItem.getMenuItems();
	        long menuItemId = menuItem.getMenuitemId();
	        // You may want to retrieve the menuItem from the repository here if needed

	        // Set the menu item for the cart item
	        menuItem = menuItemsRepo.findById(menuItemId)
	                .orElseThrow(() -> new EntityNotFoundException("Menu item not found with id: " + menuItemId));
	        cartItem.setMenuItems(menuItem);

	        
	    }

	    cartMenuItemsRepo.saveAll(cartItems);


	    return cartRepository.save(cart);

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

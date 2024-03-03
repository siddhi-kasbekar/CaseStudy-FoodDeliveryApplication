package com.hexaware.hotpot.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hexaware.hotpot.dto.CartDetailsDTO;
import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.entities.Cart;
import com.hexaware.hotpot.entities.CartDetails;
import com.hexaware.hotpot.entities.Discount;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.exception.CustomerNotFoundException;
import com.hexaware.hotpot.exception.DiscountNotFoundException;
import com.hexaware.hotpot.repository.CartDetailsRepository;
import com.hexaware.hotpot.repository.CartRepository;
import com.hexaware.hotpot.repository.CustomersRepository;
import com.hexaware.hotpot.repository.DiscountRepository;
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
	
	private boolean clearingAndAdding = false;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CustomersRepository customerRepo;

	@Autowired
	private CartDetailsRepository cartDetailsRepo;

	@Autowired
	private MenuItemsRepository menuItemsRepo;
	
	@Autowired
	DiscountRepository discountRepo;
	
	
	private static final Logger log = LoggerFactory.getLogger(CartServiceImp.class);


	@Override
	public Cart getCartItems(Long customerId) {

		return cartRepository.findByCustomerId(customerId);
	}

	@Override
	public Cart saveCartState(long customerId, List<MenuItemsDTO> menuItemsDTO, double total)
			throws CustomerNotFoundException {
		
		Cart existingCart = cartRepository.findByCustomerId(customerId);
		Cart cart;
		if (existingCart != null) {
			
			existingCart.setTotal(total);
			
			return cartRepository.save(existingCart);
		} else {
			
			cart = new Cart();
			cart.setTotal(total); 
			cart.setCustomerId(customerId);

			
			cartRepository.save(cart);
		}

		
		for (MenuItemsDTO menuItemDTO : menuItemsDTO) {
			
			MenuItems menuItem = menuItemsRepo.findById(menuItemDTO.getMenuItemId())
					.orElseThrow(() -> new RuntimeException("Menu item not found"));

			CartDetails cartDetails = new CartDetails();
			cartDetails.setCart(cart);
			cartDetails.setMenuItems(menuItem);
			cartDetails.setQuantity(menuItemDTO.getQuantity());

			
			cartDetailsRepo.save(cartDetails);
		}

		return cart;
	}
	
	
	
	
	@Override
	@Transactional
	public void clearCart(long customerId) throws CustomerNotFoundException {
	    
	    Cart cart = cartRepository.findByCustomerId(customerId);
	    if (cart != null) {
	        
	        cart.setTotal(0.0);
	        
	     
	        List<CartDetails> cartDetailsList = cartDetailsRepo.findByCart_CartId(cart.getCartId());
	        cartDetailsRepo.deleteAll(cartDetailsList);
	        
	     

	        
	        cartRepository.save(cart);
	        
	     
	    } else {
	        throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
	    }
	}

	@Override
	public void addToCart(Long customerId, CartDetailsDTO cartDetailsDTO) throws CustomerNotFoundException {
	    
	    Cart cart = cartRepository.findByCustomerId(customerId);

	    if (cart != null) {
	        
	        if (cart.getCartItems().isEmpty() || isSameRestaurant(cart, cartDetailsDTO)) {
	            // If the cart exists and either it's empty or the restaurant ID matches, proceed

	            // Set the total to 0
	            cart.setTotal(0);

	           
	            Optional<CartDetails> existingCartItem = cartDetailsRepo.findByCart_CartIdAndMenuItem_MenuItemId(cart.getCartId(), cartDetailsDTO.getMenuItemId());

	            if (existingCartItem.isPresent()) {
	                
	                CartDetails cartDetails = existingCartItem.get();
	                cartDetails.setQuantity(cartDetails.getQuantity() + 1);
	             
	                cartDetailsRepo.save(cartDetails);
	            } else {
	                
	                CartDetails cartDetails = createNewCartDetailsWithDTO(cart, cartDetailsDTO);
	               
	                cartDetailsRepo.save(cartDetails);
	            }

	           
	            cart.setTotal(calculateOriginTotal(cart));

	            
	            cartRepository.save(cart);
	        } else {
	        	
	            clearCartAndAddItem(customerId, cartDetailsDTO);
	        }
	    } else {
	       
	        throw new IllegalArgumentException("Cart for customer with ID " + customerId + " not found");
	    }
	}


	
	// Helper method to check if the restaurant ID matches existing items in the cart
	private boolean isSameRestaurant(Cart cart, CartDetailsDTO cartDetailsDTO) {
	    int newRestaurantId = menuItemsRepo.findById(cartDetailsDTO.getMenuItemId())
	            .map(menuItems -> menuItems.getRestaurant().getRestaurantId())
	            .orElseThrow(() -> new IllegalArgumentException("item not found"));

	    
	    log.info("New Restaurant ID: " + newRestaurantId);
	 // Check if all existing items in the cart have the same restaurant ID
	    return cart.getCartItems().stream()
	            .allMatch(cartDetails -> {
	                int existingRestaurantId = cartDetails.getMenuItems().getRestaurant().getRestaurantId();
	                log.info("Existing Restaurant ID: " + existingRestaurantId);
	                return existingRestaurantId == newRestaurantId;
	            });

	}
	
	// Helper method to clear the cart and add the new item
	private void clearCartAndAddItem(Long customerId, CartDetailsDTO cartDetailsDTO) throws CustomerNotFoundException {
	    // Add a flag to prevent infinite loop
	    if (!clearingAndAdding) {
	        clearingAndAdding = true;
	        try {
	            clearCart(customerId);
	            addToCart(customerId, cartDetailsDTO);
	        } finally {
	            clearingAndAdding = false;
	        }
	    }
	}

	
	
	private CartDetails createNewCartDetailsWithDTO(Cart cart, CartDetailsDTO cartDetailsDTO) {
	    // Fetch menu item based on menuItemId
		System.out.println(cartDetailsDTO.getMenuItemId());
	    MenuItems menuItem = menuItemsRepo.findById(cartDetailsDTO.getMenuItemId())
	            .orElseThrow(() -> new IllegalArgumentException("item cannot found"));
	    

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
	public void removeFromCart(Long customerId, Long menuItemId, int quantity) {
	    // Find the cart for the given customer
	    Cart cart = cartRepository.findByCustomerId(customerId);

	    if (cart != null) {
	        
	        cart.setTotal(0);

	        
	        Optional<CartDetails> existingCartItem = cartDetailsRepo.findByCart_CartIdAndMenuItem_MenuItemId(cart.getCartId(), menuItemId);

	        if (existingCartItem.isPresent()) {
	            // If the item exists, decrement the quantity by specified quantity
	            CartDetails cartDetails = existingCartItem.get();
	            int newQuantity = cartDetails.getQuantity() - quantity;

	            if (newQuantity > 0) {
	                // If quantity is greater than 0, update the quantity
	                cartDetails.setQuantity(newQuantity);
	                cartDetailsRepo.save(cartDetails);
	            } else {
	                // If quantity becomes 0, delete the record from cart_details
	                cartDetailsRepo.delete(cartDetails);
	            }

	           
	            cart.setTotal(calculateOriginTotal(cart));

	           
	            cartRepository.save(cart);
	        } else {
	            
	            throw new IllegalArgumentException("Item not found in the cart");
	        }
	    } else {
	        
	        throw new IllegalArgumentException("Cart for customer with ID " + customerId + " not found");
	    }
	}

	

	
	@Override
	@Transactional
	public void calculateDiscountedTotal(Long customerId, LocalDate currentDate) throws DiscountNotFoundException {
	    Cart cart = cartRepository.findByCustomerId(customerId);

	    if (cart != null) {
	        Discount discount = discountRepo.findByStartDateBeforeAndEndDateAfter(currentDate, currentDate)
		            .orElseThrow(() -> new DiscountNotFoundException("No active discount found for the current date"));

	        if (discount != null) {
	            int discountPercentage = discount.getDiscountPercentage();
	            double total = cart.getTotal();
	            double discountedTotal = total - (total * discountPercentage / 100);
	            cart.setTotal(discountedTotal);
	            cartRepository.save(cart);
	        }
	    }
	}


	
	@Override
	public List<Object[]> getCartDetails(long customerId) {
	    return cartRepository.getCartDetailsByCustomerId(customerId);
	}
	
	


}

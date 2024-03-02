package com.hexaware.hotpot.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
	        
	     // Delete the cart details associated with the customer's cart
	        List<CartDetails> cartDetailsList = cartDetailsRepo.findByCart_CartId(cart.getCartId());
	        cartDetailsRepo.deleteAll(cartDetailsList);
	        
	     

	        // Save the updated cart
	        cartRepository.save(cart);
	        
	     
	    } else {
	        throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
	    }
	}

	@Override
	public void addToCart(Long customerId, CartDetailsDTO cartDetailsDTO) throws CustomerNotFoundException {
	    // Find the cart for the given customer
	    Cart cart = cartRepository.findByCustomerId(customerId);

	    if (cart != null) {
	        // Check if the cart is empty or if the restaurant ID matches the existing items
	        if (cart.getCartItems().isEmpty() || isSameRestaurant(cart, cartDetailsDTO)) {
	            // If the cart exists and either it's empty or the restaurant ID matches, proceed

	            // Set the total to 0
	            cart.setTotal(0);

	            // Check if the item already exists in the cart
	            Optional<CartDetails> existingCartItem = cartDetailsRepo.findByCart_CartIdAndMenuItem_MenuItemId(cart.getCartId(), cartDetailsDTO.getMenuItemId());

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
	        	// If the restaurant ID is different, clear the cart and add the new item
	            clearCartAndAddItem(customerId, cartDetailsDTO);
	        }
	    } else {
	        // If the cart is not found, you might want to handle this scenario
	        throw new IllegalArgumentException("Cart for customer with ID " + customerId + " not found");
	    }
	}


	
	// Helper method to check if the restaurant ID matches existing items in the cart
	private boolean isSameRestaurant(Cart cart, CartDetailsDTO cartDetailsDTO) {
	    int newRestaurantId = menuItemsRepo.findById(cartDetailsDTO.getMenuItemId())
	            .map(menuItems -> menuItems.getRestaurant().getRestaurantId())
	            .orElseThrow(() -> new IllegalArgumentException("Menu item not found"));

	    
	    System.out.println("New Restaurant ID: " + newRestaurantId);
	 // Check if all existing items in the cart have the same restaurant ID
	    return cart.getCartItems().stream()
	            .allMatch(cartDetails -> {
	                int existingRestaurantId = cartDetails.getMenuItems().getRestaurant().getRestaurantId();
	                System.out.println("Existing Restaurant ID: " + existingRestaurantId);
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
	public void removeFromCart(Long customerId, Long menuItemId, int quantity) {
	    // Find the cart for the given customer
	    Cart cart = cartRepository.findByCustomerId(customerId);

	    if (cart != null) {
	        // If the cart exists, update the total
	        cart.setTotal(0);

	        // Check if the item already exists in the cart
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

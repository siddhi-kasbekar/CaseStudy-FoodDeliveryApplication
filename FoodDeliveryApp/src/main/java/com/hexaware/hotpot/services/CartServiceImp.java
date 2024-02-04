package com.hexaware.hotpot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.hotpot.dto.CartDTO;
import com.hexaware.hotpot.entities.Cart;
import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.repository.CartRepository;
import com.hexaware.hotpot.repository.CustomersRepository;

@Service
public class CartServiceImp implements ICartService {
	
	@Autowired
    private CartRepository cartRepository;
	
	@Autowired
	private CustomersRepository customerRepo;
	

	@Override
	public int addToCart(Long customerId, Long menuId, int quantity) {
		
//	    Optional<Customers> customerOptional = customerRepo.findById(customerId);
//	    if (customerOptional.isPresent()) {
//	        Customers customer = customerOptional.get();
//
//	        // Check if the item already exists in the cart
//	        Cart existingCartItem = cartRepository.findByCustomer_CustIdAndMenuItemSet_Menuitem_id(customerId, menuId);
//
//	        if (existingCartItem != null) {
//	            // If the item exists, update quantity
//	            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
//	            Cart updatedCartItem = cartRepository.save(existingCartItem);
//	            return updatedCartItem.getCartId();
//	        }
//	        else {
//	            // Create a new cart item
//	        	CartDTO cartDTO = new CartDTO();
//	            cartDTO.setCustomerId(customerId);
//	            cartDTO.setMenuId(menuId);
//	            cartDTO.setQuantity(quantity);
//	            
//	            Cart newCartItem = new Cart();
//	            newCartItem.setCustomerId(customerId);
//	            newCartItem.setMenuId(menuId);
//	            newCartItem.setQuantity(quantity);
//	            
//	            newCartItem = cartRepository.save(newCartItem);
//	            
//	            return newCartItem.getCartId();
//
//
//	        }
//	    }
		return 0;

	}

	@Override
	public void removeFromCart(long customerId, long menuId) {
		Cart cartItem = cartRepository.findByCustomer_CustIdAndMenuItemSet_Menuitem_id(customerId, menuId);

        if (cartItem != null) {
            cartRepository.deleteById(cartItem.getCartId());
        }
		

	}

	@Override
	public void updateCartQuantity(Long customerId, Long menuId, int newQuantity) {
		

	}

	@Override
	public List<Cart> getCartItems(Long customerId) {
		
		return cartRepository.findByCustomer_CustId(customerId);
	}

}

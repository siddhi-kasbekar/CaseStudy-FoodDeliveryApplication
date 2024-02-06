package com.hexaware.hotpot.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.hotpot.entities.Cart;
import com.hexaware.hotpot.entities.CartMenuItems;
import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.repository.CartMenuitemsRepository;
import com.hexaware.hotpot.repository.CartRepository;
import com.hexaware.hotpot.repository.CustomersRepository;
import com.hexaware.hotpot.repository.MenuItemsRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CartServiceImp implements ICartService {
	
	@Autowired
    private CartRepository cartRepository;
	
	@Autowired
	private CustomersRepository customerRepo;
	
	@Autowired
    private MenuItemsRepository menuItemsRepo;
	
	@Autowired
    private CartMenuitemsRepository cartMenuItemsRepo;

	





//	@Override
//	public void removeFromCart(long customerId, long menuItemId) {
//        Customers customer = customerRepo.findById(customerId)
//                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + customerId));
//
//        MenuItems menuItem = menuItemsRepo.findById(menuItemId)
//                .orElseThrow(() -> new EntityNotFoundException("Menu item not found with id: " + menuItemId));
//
//        if (customer.getCart().getMenuItemSet().contains(menuItem)) {
//            customer.getCart().getMenuItemSet().remove(menuItem);
//            
//            
//            
//         // Set the quantity to 0
//            customer.getCart().setQuantity(0);
//            updateCartTotal(customer.getCart());
//            cartRepository.save(customer.getCart());
//        }
//    }

	

	@Override
	public List<Cart> getCartItems(Long customerId) {
		
		return cartRepository.findByCustomer_CustId(customerId);
	}



	@Override
	public void addToCart(long customerId, long menuItemId, int quantity) {
        Customers customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + customerId));

        MenuItems menuItem = menuItemsRepo.findById(menuItemId)
                .orElseThrow(() -> new EntityNotFoundException("Menu item not found with id: " + menuItemId));

        Cart cart = customer.getCart();

        CartMenuItems existingCartItem = findCartItemByMenuItem(cart, menuItem);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
        } else {
            CartMenuItems cartItem = new CartMenuItems(cart, menuItem, quantity);
            cart.getCartMenuItems().add(cartItem);
        }

        updateCartTotal(cart);

        cartRepository.save(cart);

        cartMenuItemsRepo.saveAll(cart.getCartMenuItems());
    }

    private CartMenuItems findCartItemByMenuItem(Cart cart, MenuItems menuItem) {
        for (CartMenuItems cartItem : cart.getCartMenuItems()) {
            if (cartItem.getMenuItems().equals(menuItem)) {
                return cartItem;
            }
        }
        return null;
    }

    private void updateCartTotal(Cart cart) {
        double total = 0.0;

        for (CartMenuItems cartItem : cart.getCartMenuItems()) {
            total += cartItem.getMenuItems().getPrice() * cartItem.getQuantity();
        }

        cart.setTotal(total);


		
	}



	@Override
	public void removeFromCart(long customerId, long menuId) {
		// TODO Auto-generated method stub
		
	}

}

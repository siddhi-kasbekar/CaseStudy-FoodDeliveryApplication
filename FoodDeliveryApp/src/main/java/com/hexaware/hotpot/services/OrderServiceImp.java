package com.hexaware.hotpot.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.hotpot.dto.OrdersDTO;
import com.hexaware.hotpot.entities.Cart;
import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.OrderDetails;
import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.repository.CartRepository;
import com.hexaware.hotpot.repository.CustomersRepository;
import com.hexaware.hotpot.repository.MenuItemsRepository;
import com.hexaware.hotpot.repository.OrderDetailsRepository;
import com.hexaware.hotpot.repository.OrdersRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImp implements IOrderService {

	@Autowired
	OrdersRepository ordersRepo;

	@Autowired
	OrderDetailsRepository orderDetailsrepo;

	@Autowired
	CartRepository cartRepo;
	
	
	@Autowired
	CustomersRepository customerRepo;
	
	@Autowired
	MenuItemsRepository  menuItemRepo;

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImp.class);

	// @Override
//	public void placeOrder(OrdersDTO orderDTO,int cartId) {
//
//		logger.info("Your order has ben placed");
//
//		Orders order = new Orders();
//		order.setOrderDate(orderDTO.getOrderDate());
//		//order.setTotalCost(orderDTO.getTotalCost()); // should come from cart
//		order.setStatus(orderDTO.getStatus());
//		order.setCustomer(orderDTO.getCustomerId());
//		order.setRestaurant(orderDTO.getRestaurantId());    //should come from restaurant
//
//		OrderDetails orderDetails = new OrderDetails();
//		orderDetails.setOrder(order); // Associate order with order details using generated order ID
//		orderDetailsrepo.save(orderDetails);
//		
//		MenuItems menuItem= new MenuItems();
//		orderDetails.setMenuItem(menuItem);
//		orderDetailsrepo.save(orderDetails);
//		
//		Cart cart = order.getCart();
//		cartRepo.findById(cartId);
//		
//		
//		ordersRepo.save(order);
//	}

	@Override
	public void placeOrder(OrdersDTO orderDTO, int cartId, long customerId, List<Long> menuItemIds) {
	    logger.info("Your order has been placed");

	    // Fetch the cart entity associated with the provided cart ID
	    Optional<Cart> cartOptional = cartRepo.findById(cartId);
	    if (!cartOptional.isPresent()) {
	        logger.error("Cart with ID " + cartId + " not found.");
	        // Handle the case where the cart does not exist
	        return;
	    }

	    Cart cart =  new Cart();                                //cartOptional.get();

	    // Fetch the customer entity associated with the provided customer ID
	    Optional<Customers> customerOptional = customerRepo.findById(customerId);
	    if (!customerOptional.isPresent()) {
	        logger.error("Customer with ID " + customerId + " not found.");
	        // Handle the case where the customer does not exist
	        return;
	    }

	    Customers customer = new Customers();                                     //customerOptional.get();

	    Orders order = new Orders();
	    order.setOrderDate(orderDTO.getOrderDate());
	    order.setTotalCost(cart.getTotal()); // Set the total cost equal to the cart total
	    order.setStatus(orderDTO.getStatus());
	    order.setCustomer(customer);
	    
	    // Fetch the restaurant ID associated with the first menu item
	    Optional<MenuItems> firstMenuItemOptional = menuItemRepo.findById(menuItemIds.get(0));
	    if (!firstMenuItemOptional.isPresent()) {
	        logger.error("Menu item with ID " + menuItemIds.get(0) + " not found.");
	        return;
	    }
	    MenuItems firstMenuItem = firstMenuItemOptional.get();
	    order.setRestaurant(firstMenuItem.getRestaurant()); // Set the restaurant of the order based on the first menu item

	    // Create and save order
	    order = ordersRepo.save(order);

	    // Create and save order details for each menu item
	    for (Long menuItemId : menuItemIds) {
	        Optional<MenuItems> menuItemOptional = menuItemRepo.findById(menuItemId);
	        if (!menuItemOptional.isPresent()) {
	            logger.error("Menu item with ID " + menuItemId + " not found.");
	            continue; 
	        }
	        MenuItems menuItem = menuItemOptional.get();
	        
	        OrderDetails orderDetails = new OrderDetails();
	        orderDetails.setOrder(order);
	        orderDetails.setMenuItem(menuItem);
	        orderDetailsrepo.save(orderDetails);
	    }
	}


	@Override
	public Orders getOrderById(int orderId) {

		return ordersRepo.findById(orderId);
	}

	@Override
	public void updateOrderStatus(int orderId, String status) {

		Orders order = ordersRepo.findById(orderId);
		if (order != null) {
			order.setStatus(status);
			ordersRepo.saveAndFlush(order);
		}
		logger.info("Order statuts has been updated");
	}

	@Override
	public List<Orders> viewOrderHistory(int customerId) {

		
		return ordersRepo.findByCustomerCustomerId(customerId);

	}

}

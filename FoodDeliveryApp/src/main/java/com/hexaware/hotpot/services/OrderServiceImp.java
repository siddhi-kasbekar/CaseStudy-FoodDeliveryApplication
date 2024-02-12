package com.hexaware.hotpot.services;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.OrderDetails;
import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.entities.Restaurants;
import com.hexaware.hotpot.exception.CustomerNotFoundException;
import com.hexaware.hotpot.exception.OrderNotFoundException;
import com.hexaware.hotpot.exception.RestaurantNotFoundException;
import com.hexaware.hotpot.repository.CustomersRepository;
import com.hexaware.hotpot.repository.MenuItemsRepository;
import com.hexaware.hotpot.repository.OrderDetailsRepository;
import com.hexaware.hotpot.repository.OrdersRepository;
import com.hexaware.hotpot.repository.RestaurantsRepository;

import jakarta.transaction.Transactional;

/*
 * Author name:NipurnaBandi
 * 
 * Class Description:contains business logic and functionalities related to Orders and also handles crud operations.
 * 
 */

@Service
@Transactional
public class OrderServiceImp implements IOrderService {

	@Autowired
	OrdersRepository ordersRepo;

	@Autowired
	OrderDetailsRepository orderDetailsrepo;
	
	@Autowired
	CustomersRepository customerRepo;
	
	@Autowired
	RestaurantsRepository restaurantRepo;
	
	@Autowired
	MenuItemsRepository menuItemRepo;

	
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImp.class);

	@Override
	public void placeOrder(long customerId, int restaurantId, List<MenuItemsDTO> menuItems, double totalCost) throws RestaurantNotFoundException, CustomerNotFoundException {
	    logger.info("Your order has been placed");

	    // Retrieve customer and restaurant entities from their IDs
	    Customers customer = customerRepo.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
	    Restaurants restaurant = restaurantRepo.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));

	    Orders order = new Orders();
	    order.setOrderDate(LocalDateTime.now()); // Assuming you set the order date to current date and time
	    // Calculate total cost based on menu items
	   // double totalCost = menuItems.stream().mapToDouble(MenuItemsDTO::getPrice).sum();
	   // order.setTotalCost(totalCost);
	    order.setTotalCost(totalCost); //will be fetched from cart
	    order.setStatus("Placed"); // Assuming initial status is "Placed"
	    order.setCustomer(customer);
	    order.setRestaurant(restaurant);
	    

	    ordersRepo.save(order);

	    // Save menu items in order details table
	    for (MenuItemsDTO menuItemDTO : menuItems) {
	        MenuItems menuItem = menuItemRepo.findById(menuItemDTO.getMenuItemId())
	                                         .orElseThrow(() -> new RuntimeException("Menu item not found"));

	        OrderDetails orderDetails = new OrderDetails();
	        orderDetails.setOrder(order);
	        orderDetails.setMenuItem(menuItem);
	        orderDetails.setQuantity(menuItemDTO.getQuantity()); // Assuming you have quantity in MenuItemDTO
	        
	        
	        orderDetailsrepo.save(orderDetails);
	    }
	}


	@Override
	public Orders getOrderById(int orderId) throws OrderNotFoundException{
	    
	Orders order = ordersRepo.findById(orderId);
    if (order == null) {
        throw new OrderNotFoundException("Order not found with ID: " + orderId);
    }
    return order;
	}
		
		
	

	@Override
	public String updateOrderStatus(int orderId, String status) throws OrderNotFoundException{

		Orders order = ordersRepo.findById(orderId);
		if (order != null) {
			order.setStatus(status);
			ordersRepo.saveAndFlush(order);
			
			logger.info("Order statuts has been updated");
		}
		else {
			throw new OrderNotFoundException("Order not found with ID: " + orderId);
		}
		return status;
		
	}

	@Override
	public List<Orders> viewOrderHistory(long customerId) {

		
		return ordersRepo.findByCustomerCustomerId(customerId);

	}

}

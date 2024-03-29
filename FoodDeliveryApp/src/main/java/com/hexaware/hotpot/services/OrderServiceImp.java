package com.hexaware.hotpot.services;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.entities.Cart;
import com.hexaware.hotpot.entities.CartDetails;
import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.OrderDetails;
import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.exception.CustomerNotFoundException;
import com.hexaware.hotpot.exception.OrderNotFoundException;
import com.hexaware.hotpot.exception.RestaurantNotFoundException;
import com.hexaware.hotpot.repository.CartDetailsRepository;
import com.hexaware.hotpot.repository.CartRepository;
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
	
	@Autowired
	CartRepository cartRepo;
	
	@Autowired
	CartDetailsRepository cartDetailsRepo;
	
	@Autowired
    private JavaMailSender javaMailSender;

	
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImp.class);

	@Override
	public void placeOrder(long customerId, List<MenuItemsDTO> menuItems, double totalCost) throws RestaurantNotFoundException, CustomerNotFoundException {
	    logger.info("Your order has been placed");

	    
	    Customers customer = customerRepo.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

	    Orders order = new Orders();
	    order.setOrderDate(LocalDateTime.now()); 
	    order.setTotalCost(totalCost);
	    order.setStatus("Placed"); 
	    order.setCustomer(customer);
	    

	    ordersRepo.save(order);

	  
	    for (MenuItemsDTO menuItemDTO : menuItems) {
	        MenuItems menuItem = menuItemRepo.findById(menuItemDTO.getMenuItemId())
	                                         .orElseThrow(() -> new RuntimeException("Menu item not found"));

	     // Set the restaurant in the order from the menu item
	        order.setRestaurant(menuItem.getRestaurant());
	        
	     
	        ordersRepo.save(order);
	        
	        OrderDetails orderDetails = new OrderDetails();
	        orderDetails.setOrder(order);
	        orderDetails.setMenuItem(menuItem);
	        orderDetails.setQuantity(menuItemDTO.getQuantity()); 
	        
	        
	        orderDetailsrepo.save(orderDetails);
	    }
	    
	 // Reset the total cost of the customer's cart to 0
	    Cart cart = cartRepo.findByCustomerId(customerId);
	    if (cart != null) {
	        cart.setTotal(0); // Set the total cost to 0
	        cartRepo.save(cart); // Save the updated cart
	    }

	    // Delete the cart details associated with the customer's cart
	    List<CartDetails> cartDetailsList = cartDetailsRepo.findByCart_CartId(cart.getCartId());
	    cartDetailsRepo.deleteAll(cartDetailsList);
	    
	    
	 // Send an email to the customer
        sendOrderConfirmationEmail(customer.getEmail(), order);
	}
	
	private void sendOrderConfirmationEmail(String customerEmail, Orders order) {
        String message = String.format("Your order has been placed successfully. Order ID: %d. Total Cost: Rs.%.2f",
                order.getOrderId(), order.getTotalCost());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(customerEmail);
        mailMessage.setSubject("Order Confirmation");
        mailMessage.setText(message);

        // Send the email
        javaMailSender.send(mailMessage);
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
			
			// Get customer details from the order
	        Customers customer = order.getCustomer();
	        String customerEmail = customer.getEmail();
			
			order.setStatus(status);
			ordersRepo.saveAndFlush(order);
			
			// Send email to the customer
	        sendOrderStatusUpdateEmail(customerEmail, orderId, status);
			
			logger.info("Order statuts has been updated");
		}
		else {
			throw new OrderNotFoundException("Order not found with ID: " + orderId);
		}
		return status;
		
	}
	
	//send email
	private void sendOrderStatusUpdateEmail(String customerEmail, int orderId, String status) {
	    String message = String.format("Thank you for ordering from HOTBYTE. Enjoy your food !! Your order (ID: %d) status has been updated to: %s", orderId, status);

	    SimpleMailMessage mailMessage = new SimpleMailMessage();
	    mailMessage.setTo(customerEmail);
	    mailMessage.setSubject("Order Status Update");
	    mailMessage.setText(message);

	    
	    javaMailSender.send(mailMessage);
	}

	@Override
	public List<Object[]>  viewOrderHistory(long customerId) {

		
		return ordersRepo.findOrderDetailsAndOrdersByCustomerId(customerId);

	}


	

}

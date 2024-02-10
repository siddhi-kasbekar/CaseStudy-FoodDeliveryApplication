package com.hexaware.hotpot.services;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.hotpot.dto.OrdersDTO;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.OrderDetails;
import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.exception.OrderNotFoundException;
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

	
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImp.class);

	 @Override
	public void placeOrder(OrdersDTO orderDTO,long customerId) {

		logger.info("Your order has been placed");

		Orders order = new Orders();
		order.setOrderDate(orderDTO.getOrderDate());
		order.setTotalCost(orderDTO.getTotalCost()); // should come from cart
		order.setStatus(orderDTO.getStatus());
		order.setCustomer(orderDTO.getCustomerId());
		order.setRestaurant(orderDTO.getRestaurantId());    //should come from restaurant

		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setOrder(order); // Associate order with order details using generated order ID
		orderDetailsrepo.save(orderDetails);
		
		MenuItems menuItem= new MenuItems();
		orderDetails.setMenuItem(menuItem);
		orderDetailsrepo.save(orderDetails);
		
	
		
		
		ordersRepo.save(order);
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
	public void updateOrderStatus(int orderId, String status) throws OrderNotFoundException{

		Orders order = ordersRepo.findById(orderId);
		if (order != null) {
			order.setStatus(status);
			ordersRepo.saveAndFlush(order);
			
			logger.info("Order statuts has been updated");
		}
		else {
			throw new OrderNotFoundException("Order not found with ID: " + orderId);
		}
		
	}

	@Override
	public List<Orders> viewOrderHistory(long customerId) {

		
		return ordersRepo.findByCustomerCustomerId(customerId);

	}

}

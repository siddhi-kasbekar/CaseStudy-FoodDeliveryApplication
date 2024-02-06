package com.hexaware.hotpot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.hotpot.dto.OrdersDTO;
import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.repository.OrdersRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImp implements IOrderService {

	@Autowired
	OrdersRepository repo;
	
	@Override
	public Orders placeOrder(OrdersDTO orderDTO) {
		 Orders order = new Orders();
		    //orderDTO.setOrderId(orderDTO.getOrderId());
	        order.setOrderDate(orderDTO.getOrderDate());
	        order.setTotalCost(orderDTO.getTotalCost());
	        order.setStatus(orderDTO.getStatus());
	        //order.setCart(orderDTO.getCartId());  // Assuming there is a setCartId method in the Orders entity
	        order.setCustomer(orderDTO.getCustomerId());
	        order.setRestaurant(orderDTO.getRestaurantId());

	         return repo.save(order);
	       
		
	}

	@Override
	public Orders getOrderById(int orderId) {
		
		return repo.findById(orderId);
	}

	@Override
	public String updateOrderStatus(int orderId, String status) {
		
		Orders order = repo.findById(orderId);
		if (order != null) {
            order.setStatus(status);
            repo.saveAndFlush(order);
        }
		return "order updated";
	}

	@Override
	public List<Orders> viewOrderHistory(int customerId) {
		
		return repo.findByCustomerCustId(customerId);
	}

}

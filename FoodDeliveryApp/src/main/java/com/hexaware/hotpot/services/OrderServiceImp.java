package com.hexaware.hotpot.services;

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
	public void placeOrder(OrdersDTO orderDTO) {
//		 Orders order = new Orders();
//	        order.setOrderDate(orderDTO.getOrderDate());
//	        order.setTotalCost(orderDTO.getTotalCost());
//	        order.setStatus(orderDTO.getStatus());
//	        order.setCart(orderDTO.getCartId());  // Assuming there is a setCartId method in the Orders entity
//	        order.setCustomerId(orderDTO.getCustomerId());
//	        order.setRestaurantId(orderDTO.getRestaurantId());
//
//	        Orders savedOrder = repo.save(order);
//	        return savedOrder.getOrderId();
		
	}

	@Override
	public Orders getOrderById(int orderId) {
		
		return repo.findById(orderId);
	}

	@Override
	public void updateOrderStatus(int orderId, String status) {
		
		Orders order = repo.findById(orderId);
		if (order != null) {
            order.setStatus(status);
            repo.saveAndFlush(order);
        }
	}

}

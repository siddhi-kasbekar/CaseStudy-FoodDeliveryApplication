package com.hexaware.hotpot.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.hexaware.hotpot.dto.OrdersDTO;
import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.repository.OrdersRepository;

public class OrderServiceImp implements IOrderService {

	@Autowired
	OrdersRepository repo;
	
	@Override
	public Long placeOrder(OrdersDTO orderDTO) {
		
		return null;
	}

	@Override
	public Orders getOrderById(Long orderId) {
		
		return null;
	}

	@Override
	public void updateOrderStatus(Long orderId, String status) {
		

	}

}

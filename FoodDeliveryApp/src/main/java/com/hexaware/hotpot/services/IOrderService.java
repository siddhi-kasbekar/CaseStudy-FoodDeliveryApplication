package com.hexaware.hotpot.services;

import java.util.List;
import java.util.Optional;

import com.hexaware.hotpot.dto.OrdersDTO;
import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.exception.OrderNotFoundException;

public interface IOrderService {

	public void placeOrder(OrdersDTO orderDTO,long customerId);

	public Orders getOrderById(int orderId) throws OrderNotFoundException;

	public void updateOrderStatus(int orderId,String status) throws OrderNotFoundException;
	
	public List<Orders> viewOrderHistory(long customerId);
	
}

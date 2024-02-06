package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.dto.OrdersDTO;
import com.hexaware.hotpot.entities.Orders;

public interface IOrderService {

	public Orders placeOrder(OrdersDTO orderDTO);

	public Orders getOrderById(int orderId);

	public String updateOrderStatus(int orderId,String status);
	
	public List<Orders> viewOrderHistory(int customerId);
	
}

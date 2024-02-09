package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.dto.OrdersDTO;
import com.hexaware.hotpot.entities.Orders;

public interface IOrderService {

	public void placeOrder(OrdersDTO orderDTO, int cartId, long customerId, List<Long> menuItemIds);

	public Orders getOrderById(int orderId);

	public void updateOrderStatus(int orderId,String status);
	
	public List<Orders> viewOrderHistory(int customerId);
	
}

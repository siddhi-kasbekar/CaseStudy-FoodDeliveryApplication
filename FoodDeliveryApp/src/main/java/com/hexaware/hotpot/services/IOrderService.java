package com.hexaware.hotpot.services;

import com.hexaware.hotpot.dto.OrdersDTO;
import com.hexaware.hotpot.entities.Orders;

public interface IOrderService {

	public void placeOrder(OrdersDTO orderDTO);

	public Orders getOrderById(Long orderId);

	public void updateOrderStatus(Long orderId, String status);
}

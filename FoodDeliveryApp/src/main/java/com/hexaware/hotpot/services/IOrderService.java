package com.hexaware.hotpot.services;

import com.hexaware.hotpot.dto.OrdersDTO;
import com.hexaware.hotpot.entities.Orders;

public interface IOrderService {

	public Orders placeOrder(OrdersDTO orderDTO);

	public Orders getOrderById(int orderId);

	public void updateOrderStatus(int orderId,String status);
}

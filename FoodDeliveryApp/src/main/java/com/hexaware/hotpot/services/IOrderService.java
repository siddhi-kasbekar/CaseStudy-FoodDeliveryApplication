package com.hexaware.hotpot.services;

import com.hexaware.hotpot.entities.Orders;

public interface IOrderService {

	public Long placeOrder(Orders order);

	public Orders getOrderById(Long orderId);

	public void updateOrderStatus(Long orderId, String status);
}

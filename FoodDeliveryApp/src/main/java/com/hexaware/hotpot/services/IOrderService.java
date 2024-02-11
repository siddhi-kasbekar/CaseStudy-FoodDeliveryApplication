package com.hexaware.hotpot.services;

import java.util.List;
import java.util.Map;

import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.exception.CustomerNotFoundException;
import com.hexaware.hotpot.exception.OrderNotFoundException;
import com.hexaware.hotpot.exception.RestaurantNotFoundException;

public interface IOrderService {

	public void placeOrder(long customerId, int restaurantId, List<MenuItemsDTO> menuItems)throws RestaurantNotFoundException,CustomerNotFoundException ;

	public Orders getOrderById(int orderId) throws OrderNotFoundException;

	public void updateOrderStatus(int orderId,String status) throws OrderNotFoundException;
	
	public List<Orders> viewOrderHistory(long customerId);
	
}

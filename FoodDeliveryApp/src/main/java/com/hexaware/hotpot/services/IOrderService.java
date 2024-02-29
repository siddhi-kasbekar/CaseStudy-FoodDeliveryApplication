package com.hexaware.hotpot.services;

import java.util.List;


import com.hexaware.hotpot.dto.MenuItemsDTO;
 
import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.exception.CustomerNotFoundException;
import com.hexaware.hotpot.exception.OrderNotFoundException;
import com.hexaware.hotpot.exception.RestaurantNotFoundException;

public interface IOrderService {

	public void placeOrder(long customerId,  List<MenuItemsDTO> menuItems, double totalCost)throws RestaurantNotFoundException,CustomerNotFoundException ;

	public Orders getOrderById(int orderId) throws OrderNotFoundException;

	public String updateOrderStatus(int orderId,String status) throws OrderNotFoundException;
	
	public List<Object[]>  viewOrderHistory(long customerId);
	
}

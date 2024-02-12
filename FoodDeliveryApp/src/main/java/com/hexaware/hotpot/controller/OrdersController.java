package com.hexaware.hotpot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.exception.CustomerNotFoundException;
import com.hexaware.hotpot.exception.OrderNotFoundException;
import com.hexaware.hotpot.exception.RestaurantNotFoundException;
import com.hexaware.hotpot.services.IOrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/order")
public class OrdersController {

	@Autowired
	IOrderService service;
	
	@PostMapping("/placeOrder")
    @PreAuthorize("hasAuthority('customer')")		
	public String placeOrder(@Valid @RequestParam long customerId, @RequestParam int restaurantId, @RequestBody Map<String, Object> requestBody) throws RestaurantNotFoundException, CustomerNotFoundException {
	    List<MenuItemsDTO> menuItems = parseMenuItems(requestBody);
	    double totalCost = (double) requestBody.get("totalCost");
	    service.placeOrder(customerId, restaurantId, menuItems,totalCost);
	    return "Your order has been placed";
	}

	private List<MenuItemsDTO> parseMenuItems(Map<String, Object> requestBody) {
	    List<Map<String, Object>> menuItemList = (List<Map<String, Object>>) requestBody.get("menuItems");
	    List<MenuItemsDTO> menuItems = new ArrayList<>();
	    for (Map<String, Object> menuItemData : menuItemList) {
	        MenuItemsDTO menuItemDTO = new MenuItemsDTO();
	        menuItemDTO.setMenuItemId(Long.parseLong(menuItemData.get("menuItemId").toString()));
	        menuItemDTO.setQuantity(Integer.parseInt(menuItemData.get("quantity").toString()));
	        // Set other properties if needed
	        menuItems.add(menuItemDTO);
	    }
	    return menuItems;
	}

	@GetMapping("/getById/{orderId}")
    @PreAuthorize("hasAuthority('customer')")
	public Orders getOrderById(@PathVariable int orderId) throws OrderNotFoundException {
		return service.getOrderById(orderId);
	}

	@PutMapping("/update-status/{orderId}/{status}")
    @PreAuthorize("hasAuthority('manager')")
	public void updateOrderStatus(@PathVariable int orderId,@PathVariable String status) throws OrderNotFoundException {
		service.updateOrderStatus(orderId, status);
		
	}
	
	@GetMapping("/viewHistory/{customerId}")
    @PreAuthorize("hasAuthority('customer')")
	public List<Orders> viewOrderHistory(@PathVariable int customerId){
		return service.viewOrderHistory(customerId);
	}
}

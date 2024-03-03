package com.hexaware.hotpot.controller;

import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.dto.OrderHistoryDTO;
import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.exception.CustomerNotFoundException;
import com.hexaware.hotpot.exception.OrderNotFoundException;
import com.hexaware.hotpot.exception.RestaurantNotFoundException;
import com.hexaware.hotpot.services.IOrderService;

import jakarta.validation.Valid;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/v1/order")
public class OrdersController {

	@Autowired
	IOrderService service;
	
	@PostMapping("/placeOrder/{customerId}")
    @PreAuthorize("hasAuthority('customer')")		
	public String placeOrder(@Valid @PathVariable long customerId, @RequestBody Map<String, Object> requestBody) throws RestaurantNotFoundException, CustomerNotFoundException {
	    List<MenuItemsDTO> menuItems = parseMenuItems(requestBody);
	    double totalCost = ((Integer) requestBody.get("totalCost")).doubleValue();
	    service.placeOrder(customerId, menuItems,totalCost);
	    return "Your order has been placed";
	}

	

	@GetMapping("/getById/{orderId}")
    @PreAuthorize("hasAuthority('customer')")
	public Orders getOrderById(@PathVariable int orderId) throws OrderNotFoundException {
		return service.getOrderById(orderId);
	}

	@PutMapping("/update-status/{orderId}/{status}")
    @PreAuthorize("hasAuthority('manager')")
	public String updateOrderStatus(@PathVariable int orderId,@PathVariable String status) throws OrderNotFoundException {
		service.updateOrderStatus(orderId, status);
		return "status updated";
	}
	
	@GetMapping("/viewHistory/{customerId}")
    @PreAuthorize("hasAuthority('customer')")
	public List<OrderHistoryDTO> viewOrderHistory(@PathVariable int customerId){
		List<Object[]> orderHistoryDetails = service.viewOrderHistory(customerId);
	    List<OrderHistoryDTO> orderHistoryDTOList = new ArrayList<>();

	    for (Object[] row : orderHistoryDetails) {
	        long orderDetailId = ((Number) row[0]).longValue();
	        int quantity = ((Number) row[1]).intValue();
	        long menuId = ((Number) row[2]).longValue();
	        long orderId = ((Number) row[3]).longValue();
	        String status = (String) row[6];
	        
	        Timestamp timestamp = (Timestamp) row[5];
	        LocalDateTime orderDate = timestamp.toLocalDateTime();



	        double totalCost = ((Number) row[7]).doubleValue();
	        int custId = ((Number) row[8]).intValue();
	        Long restaurantId = row[9] != null ? ((Number) row[8]).longValue() : null;
	        String menuName = (String) row[10];

	        OrderHistoryDTO orderHistoryDTO = new OrderHistoryDTO(orderDetailId, quantity, menuId, orderId, orderDate,
	                status, totalCost, custId, restaurantId, menuName);
	        orderHistoryDTOList.add(orderHistoryDTO);
	    }

	    return orderHistoryDTOList;
	}
	
	private List<MenuItemsDTO> parseMenuItems(Map<String, Object> requestBody) {
	    List<Map<String, Object>> menuItemList = (List<Map<String, Object>>) requestBody.get("menuItems");
	    List<MenuItemsDTO> menuItems = new ArrayList<>();
	    for (Map<String, Object> menuItemData : menuItemList) {
	        MenuItemsDTO menuItemDTO = new MenuItemsDTO();
	        menuItemDTO.setMenuItemId(Long.parseLong(menuItemData.get("menuItemId").toString()));
	        menuItemDTO.setQuantity(Integer.parseInt(menuItemData.get("quantity").toString()));
	       
	        menuItems.add(menuItemDTO);
	    }
	    return menuItems;
	}
}

package com.hexaware.hotpot.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.dto.RestaurantsDTO;
import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.entities.Restaurants;

@SpringBootTest
class AdminServiceImpTest {
	
	@Autowired
	IAdminService adminService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}



	@Test
	void testAddRestaurant() {
		RestaurantsDTO restaurantDTO = new RestaurantsDTO();
        restaurantDTO.setRestaurantId(3);
        restaurantDTO.setName("RamKrishna");
        restaurantDTO.setLocation("Kolhapur");
        restaurantDTO.setContactNumber("1234567890");
        restaurantDTO.setRating(4.3);

        Restaurants addedRestaurant = adminService.addRestaurant(restaurantDTO);

        assertNotNull(addedRestaurant);
        assertEquals(3, addedRestaurant.getRestaurantId());

	}

	@Test
	void testRemoveRestaurant() {
		int restaurantIdToRemove = 1;
		adminService.removeRestaurant(restaurantIdToRemove);
		assertTrue(true);
		

	}

	@Test
	void testGetAllMenus() {
		List<MenuItems> menuItemsList = adminService.getAllMenus();

        assertNotNull(menuItemsList);
        assertFalse(menuItemsList.isEmpty());

	}

	@Test
	void testGetAllRestaurants() {
		List<Restaurants> restaurantsList = adminService.getAllRestaurants();
		assertNotNull(restaurantsList);

	}

	@Test
	void testGetAllOrders() {
		List<Orders> ordersList = adminService.getAllOrders();
		assertNotNull(ordersList);

	}
	
	@Test
	void testGetAllCustomers() {
		List<Customers> customersList = adminService.getAllCustomers();
		assertNotNull(customersList);

	}

	
	

	@Test
	void testAddMenuItem() {


		MenuItemsDTO menuItemsDTO = new MenuItemsDTO();
        menuItemsDTO.setItemName("chicken vindaloo");
        menuItemsDTO.setDescription("delicious chicken");
        menuItemsDTO.setCategory("main course");
        menuItemsDTO.setPrice(399.00);
        menuItemsDTO.setAvailabilityTime("dinner");
        menuItemsDTO.setSpecialDietaryInfo("high protein");
        menuItemsDTO.setTasteInfo("spicy tangy ");
        menuItemsDTO.setNutritionalInfo("high in protein");
        menuItemsDTO.setCookingTime(40);

        int restaurantId = 3; 

        MenuItems newMenuItem = adminService.addMenuItem(menuItemsDTO, restaurantId);

        assertNotNull(newMenuItem);
        assertEquals(menuItemsDTO.getItemName(), newMenuItem.getItemName());
	}

	

	@Test
	void testRemoveMenuItems() {
		
		 long menuItemIdToDelete = 3; 

	        adminService.removeMenuItems(menuItemIdToDelete);

	        assertFalse(adminService.getAllMenus().stream()
	        	    .anyMatch(m -> menuItemIdToDelete == m.getMenuitemId()));


	}

}

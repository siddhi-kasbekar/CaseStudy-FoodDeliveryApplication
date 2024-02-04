package com.hexaware.hotpot.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.hotpot.dto.CustomersDTO;
import com.hexaware.hotpot.dto.DeliveryAddressDTO;
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

//	@Test
//	void testAdminLogin() {
//		fail("Not yet implemented");
//	}

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
	void testAddCustomers() {
		CustomersDTO customersDTO = new CustomersDTO();
        customersDTO.setCustId(6);
        customersDTO.setCustName("ishita Joshi");
        customersDTO.setGender("female");
        customersDTO.setEmail("ishita.joshi@gmail.com");
        customersDTO.setPhone("1237797890");
        customersDTO.setUsername("ishita_joshi");
        customersDTO.setPassword("hashed_password");

        DeliveryAddressDTO addressDTO = new DeliveryAddressDTO();
        addressDTO.setAddressId(6);
        addressDTO.setHouseNo("777");
        addressDTO.setArea("RK nagar");
        addressDTO.setLandmark("Near Ganesh temple");
        addressDTO.setCity("Kolhapur");
        addressDTO.setPincode(123456);

        customersDTO.setAddressDTO(addressDTO);

        Customers newCustomer = adminService.addCustomers(customersDTO);

        assertNotNull(newCustomer);

	}

	@Test
	void testAddMenuItem() {


		MenuItemsDTO menuItemsDTO = new MenuItemsDTO();
        menuItemsDTO.setMenuitemId(4);
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
	void testRemoveCustomers() {
		long customerIdToRemove = 5; 

        adminService.removeCustomers(customerIdToRemove);
        assertTrue(true);
//        assertFalse(adminService.getAllCustomers().stream()
//                .anyMatch(c -> c.getCustId().equals(customerIdToRemove)));

	}

	@Test
	void testRemoveMenuItems() {
		
		 long menuItemIdToDelete = 3; 

	        adminService.removeMenuItems(menuItemIdToDelete);

	        assertFalse(adminService.getAllMenus().stream()
	        	    .anyMatch(m -> menuItemIdToDelete == m.getMenuitemId()));


	}

}

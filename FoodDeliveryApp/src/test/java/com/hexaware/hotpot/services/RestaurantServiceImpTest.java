package com.hexaware.hotpot.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.dto.RestaurantsDTO;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Restaurants;

@SpringBootTest
class RestaurantServiceImpTest {

	@Autowired
	IRestaurantService rservice;
	
	
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testRegisterRestaurant() {
		RestaurantsDTO restaurantDTO=new RestaurantsDTO();
		restaurantDTO.setRestaurantId(2);
		restaurantDTO.setName("Imperial Sabre");
		restaurantDTO.setLocation("Pune");
		restaurantDTO.setContactNumber("8839953452");
		restaurantDTO.setRating(4.1);
		
		Restaurants restaurant=rservice.registerRestaurant(restaurantDTO);
		assertNotNull(restaurant);
		assertEquals(restaurantDTO.getRestaurantId(), restaurant.getRestaurantId());
	}

	@Test
	void testLoginRestaurant() {
		
	}

	
	@Test
	void testAddMenu() {

		MenuItemsDTO menuItemsDTO = new MenuItemsDTO();
        menuItemsDTO.setMenuitemId(2);
        menuItemsDTO.setItemName("Butter chicken");
        menuItemsDTO.setDescription("delicious chicken");
        menuItemsDTO.setCategory("main course");
        menuItemsDTO.setPrice(399.00);
        menuItemsDTO.setAvailabilityTime("dinner");
        menuItemsDTO.setSpecialDietaryInfo("high protein");
        menuItemsDTO.setTasteInfo("spicy tangy ");
        menuItemsDTO.setNutritionalInfo("high in protein");
        menuItemsDTO.setCookingTime(40);

        int restaurantId = 1; 

        MenuItems newMenuItem = rservice.addMenu(menuItemsDTO, restaurantId);

        assertNotNull(newMenuItem);
        assertEquals(menuItemsDTO.getItemName(), newMenuItem.getItemName());
	}

	@Test
	void testUpdateMenu() {
		
		MenuItemsDTO updatedMenu=new MenuItemsDTO();
		updatedMenu.setMenuitemId(2);
		updatedMenu.setItemName("dosa");
		updatedMenu.setDescription("delicious chicken");
		updatedMenu.setCategory("dinner");
		updatedMenu.setPrice(399.00);
		updatedMenu.setAvailabilityTime("dinner");
		updatedMenu.setSpecialDietaryInfo("high protein");
		updatedMenu.setTasteInfo("spicy tangy ");
		updatedMenu.setNutritionalInfo("high in protein");
		updatedMenu.setCookingTime(40);
		rservice.updateMenu(updatedMenu);
		assertTrue(true);
	}
    
	@Disabled
	@Test
	void testDeleteMenu() {
		int menuItemToRemove=2;
		rservice.deleteMenu(menuItemToRemove);
		 assertTrue(true);
	}

	@Test
	void testGetMenuByCategory() {
		
		List<MenuItems> menu = rservice.getMenuByCategory("dinner");
		boolean flag=menu.isEmpty();
		assertFalse(flag);
		
	}

	@Test
	void testSearchRestaurants() {
		List<Restaurants> list = rservice.searchRestaurants("IT");
		boolean flag=list.isEmpty();
		assertFalse(flag);
	}
	
	@Test
	void searchByLocation() {
		List<Restaurants> list = rservice.searchByLocation("Bhopal");
		boolean flag=list.isEmpty();
		assertFalse(flag);
	}
}

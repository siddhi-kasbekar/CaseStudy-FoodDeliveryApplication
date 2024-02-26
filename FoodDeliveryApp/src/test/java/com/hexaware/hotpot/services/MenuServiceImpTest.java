package com.hexaware.hotpot.services;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.exception.MenuItemNotFoundException;

@SpringBootTest
class MenuServiceImpTest {

	@Autowired
	IMenuService service;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testGetMenuByCategory() throws MenuItemNotFoundException {
		List<MenuItems> menu=service.getMenuByCategory("dinner");
		boolean flag =menu.isEmpty();
		assertFalse(flag);
	}

	@Test
	void testSearchMenuItemsByKeyword() throws MenuItemNotFoundException {
		List<MenuItems> menu=service.searchMenuItemsByKeyword("pas",601);
		boolean flag =menu.isEmpty();
		assertFalse(flag);
	}

}

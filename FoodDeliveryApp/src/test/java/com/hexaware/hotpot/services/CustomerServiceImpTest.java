package com.hexaware.hotpot.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.hotpot.dto.CustomersDTO;
import com.hexaware.hotpot.dto.DeliveryAddressDTO;
import com.hexaware.hotpot.entities.Restaurants;
import com.hexaware.hotpot.exception.LocationNotFoundException;

@SpringBootTest
class CustomerServiceImpTest {
	
	@Autowired
	private ICustomerService customerService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testRegisterCustomer() {
		
		CustomersDTO customersDTO = new CustomersDTO();
        customersDTO.setCustId(7);
        customersDTO.setCustName("Rutuja  Khorate");
        customersDTO.setGender("female");
        customersDTO.setEmail("rutuja.khorate@gmail.com");
        customersDTO.setPhone("1887797890");
        customersDTO.setUsername("rutu_khorate");
        customersDTO.setPassword("hashed_password");

        DeliveryAddressDTO addressDTO = new DeliveryAddressDTO();
        addressDTO.setAddressId(7);
        addressDTO.setHouseNo("348");
        addressDTO.setArea("Saneguruji ");
        addressDTO.setLandmark("Near ST Stand");
        addressDTO.setCity("Kolhapur");
        addressDTO.setPincode(416003);

        customersDTO.setAddressDTO(addressDTO);

        long newCustomerId = customerService.registerCustomer(customersDTO);

        //assertNotNull(newCustomerId);
        assertTrue(newCustomerId > 0);

	}

//	@Test
//	void testLoginCustomer() {
//		fail("Not yet implemented");
//	}

	@Test
	void testGetRestaurantByLocation() throws LocationNotFoundException {


		String location = "Kolhapur";
		List<Restaurants> restaurants = customerService.getRestaurantByLocation(location);
		assertNotNull(restaurants);
	}

}

package com.hexaware.hotpot.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
import com.hexaware.hotpot.exception.CustomerNotFoundException;
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



	@Test
	void testGetRestaurantByLocation() throws LocationNotFoundException {


		String location = "Kolhapur";
		List<Restaurants> restaurants = customerService.getRestaurantByLocation(location);
		assertNotNull(restaurants);
	}
	
	@Test
    void testUpdateCustomer() throws CustomerNotFoundException {
        
        // Create a DTO with updated customer information
        CustomersDTO updatedCustomerDTO = new CustomersDTO();
        updatedCustomerDTO.setCustName("Jane Doe");
        updatedCustomerDTO.setGender("Female");
        updatedCustomerDTO.setEmail("jane.doe@example.com");
        updatedCustomerDTO.setPhone("9876543210");
        updatedCustomerDTO.setUsername("janedoe");
        updatedCustomerDTO.setPassword("newpassword");

        DeliveryAddressDTO addressDTO = new DeliveryAddressDTO();
        addressDTO.setAddressId(1);
        addressDTO.setHouseNo("123");
        addressDTO.setArea("New Area");
        addressDTO.setLandmark("Near Park");
        addressDTO.setCity("City");
        addressDTO.setPincode(123456);
        updatedCustomerDTO.setAddressDTO(addressDTO);

        // Call the service method
        assertDoesNotThrow(() -> {
            long customerId = customerService.updateCustomer(1, updatedCustomerDTO);
            assertTrue(customerId > 0);
        });
    }
}



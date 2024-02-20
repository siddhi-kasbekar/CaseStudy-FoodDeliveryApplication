package com.hexaware.hotpot.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.hotpot.dto.CustomersDTO;
import com.hexaware.hotpot.dto.DeliveryAddressDTO;
import com.hexaware.hotpot.entities.Cart;
import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.DeliveryAddress;
import com.hexaware.hotpot.entities.Discount;
import com.hexaware.hotpot.entities.Restaurants;
import com.hexaware.hotpot.exception.CustomerNotFoundException;
import com.hexaware.hotpot.exception.DiscountNotFoundException;
import com.hexaware.hotpot.exception.LocationNotFoundException;
import com.hexaware.hotpot.repository.CartRepository;
import com.hexaware.hotpot.repository.CustomersRepository;
import com.hexaware.hotpot.repository.DiscountRepository;
import com.hexaware.hotpot.repository.RestaurantsRepository;

import jakarta.transaction.Transactional;

/*
 * Author name:Siddhi kasbekar
 * 
 * Class Description:contains business logic and functionalities related to Customer and also handles crud operations.
 * 
 */



@Service
@Transactional
public class CustomerServiceImp implements ICustomerService {

	@Autowired
	private CustomersRepository customerRepo;

	@Autowired
	private RestaurantsRepository restaurantRepo;
	
	

    @Autowired
    private PasswordEncoder passwordEncoder;


	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	DiscountRepository discountRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImp.class);


	@Override
	public String loginCustomer(CustomersDTO customer) {

		return null;
	}

	@Override
	public long registerCustomer(CustomersDTO customerDTO) {
		logger.info("customer registered successfully");
		Customers customer = new Customers();
//		customer.setCustId(customerDTO.getCustId());
		customer.setCustName(customerDTO.getCustName());
		customer.setGender(customerDTO.getGender());
		customer.setEmail(customerDTO.getEmail());
		customer.setPhone(customerDTO.getPhone());
		customer.setUsername(customerDTO.getUsername());
		customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
		
		
		 // Set the role to the predefined value
	    customer.setRole("customer");

		

		DeliveryAddressDTO addressDTO = customerDTO.getAddressDTO();
		if (addressDTO != null) {
			DeliveryAddress address = new DeliveryAddress();
			address.setAddressId(addressDTO.getAddressId());
			address.setHouseNo(addressDTO.getHouseNo());
			address.setArea(addressDTO.getArea());
			address.setLandmark(addressDTO.getLandmark());
			address.setCity(addressDTO.getCity());
			address.setPincode(addressDTO.getPincode());

			customer.setAddress(address);
		}

		customerRepo.save(customer);
		
		// Create a new Cart and associate it with the Customer
				Cart cart = new Cart();
				cart.setCustomerId(customer.getCustId()); // Set the Customers reference in Cart
				cart.setTotal(0);
		cartRepository.save(cart);


		return customer.getCustId();
	}
	
	
	@Override
	public long updateCustomer(long customerId, CustomersDTO updatedCustomerDTO) throws CustomerNotFoundException {

		Customers customers = customerRepo.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customerId));

//			if (updatedCustomerDTO.getCustName() != null) {
//				customer.setCustName(updatedCustomerDTO.getCustName());
//	        }   or we can use --:

		// JAVA 9 and above - Objects.requireNonNullElse


		customers.setCustName(Objects.requireNonNullElse(updatedCustomerDTO.getCustName(), customers.getCustName()));
		customers.setGender(Objects.requireNonNullElse(updatedCustomerDTO.getGender(), customers.getGender()));
		customers.setEmail(Objects.requireNonNullElse(updatedCustomerDTO.getEmail(), customers.getEmail()));
		customers.setPhone(Objects.requireNonNullElse(updatedCustomerDTO.getPhone(), customers.getPhone()));
		customers.setUsername(Objects.requireNonNullElse(updatedCustomerDTO.getUsername(), customers.getUsername()));
		customers.setPassword(Objects.requireNonNullElse(updatedCustomerDTO.getPassword(), customers.getPassword()));


		DeliveryAddressDTO updatedAddressDTO = updatedCustomerDTO.getAddressDTO();
		if (updatedAddressDTO != null) {
			DeliveryAddress existingAddress = customers.getAddress();
//			existingAddress.setAddressId(
//					Objects.requireNonNullElse(updatedAddressDTO.getAddressId(), existingAddress.getAddressId()));
			existingAddress.setHouseNo(
					Objects.requireNonNullElse(updatedAddressDTO.getHouseNo(), existingAddress.getHouseNo()));
			existingAddress.setArea(Objects.requireNonNullElse(updatedAddressDTO.getArea(), existingAddress.getArea()));
			existingAddress.setLandmark(
					Objects.requireNonNullElse(updatedAddressDTO.getLandmark(), existingAddress.getLandmark()));
			existingAddress.setCity(Objects.requireNonNullElse(updatedAddressDTO.getCity(), existingAddress.getCity()));
			existingAddress.setPincode(
					Objects.requireNonNullElse(updatedAddressDTO.getPincode(), existingAddress.getPincode()));
		}

		customerRepo.save(customers);

		return customerId;
	}

	
	@Override
	public List<Restaurants> getRestaurantByLocation(String location) throws LocationNotFoundException  {

		List<Restaurants> restaurants = restaurantRepo.findByLocation(location);

		if (restaurants.isEmpty()) {
			throw new LocationNotFoundException("No restaurants found in location: " + location);
		}

		return restaurants;
	}

	@Override
	public Discount findActiveDiscount(LocalDate currentDate) throws DiscountNotFoundException {
	    return discountRepo.findByStartDateBeforeAndEndDateAfter(currentDate, currentDate)
	            .orElseThrow(() -> new DiscountNotFoundException("No active discount found for the current date"));
	}
	

}

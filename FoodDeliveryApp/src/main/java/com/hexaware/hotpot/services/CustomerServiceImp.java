package com.hexaware.hotpot.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.hotpot.dto.CustomersDTO;
import com.hexaware.hotpot.dto.DeliveryAddressDTO;
import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.DeliveryAddress;
import com.hexaware.hotpot.entities.Restaurants;
import com.hexaware.hotpot.repository.CustomersRepository;
import com.hexaware.hotpot.repository.RestaurantsRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImp implements ICustomerService {
	
	@Autowired
	private CustomersRepository customerRepo; 
	
	@Autowired
	private RestaurantsRepository restaurantRepo;

	@Override
	public long registerCustomer(CustomersDTO customerDTO) {
		Customers customer = new Customers();
		customer.setCustId(customerDTO.getCustId());
		customer.setCustName(customerDTO.getCustName());
		customer.setGender(customerDTO.getGender());
		customer.setEmail(customerDTO.getEmail());
		customer.setPhone(customerDTO.getPhone());
		customer.setUsername(customerDTO.getUsername());
		customer.setPassword(customerDTO.getPassword());
		
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
		
		return customer.getCustId();
	}

	@Override
	public String loginCustomer(CustomersDTO customer) {
		
		return null;
	}

	@Override
	public List<Restaurants> getRestaurantByLocation(String location) {

		return restaurantRepo.findByLocation(location);
	}

}

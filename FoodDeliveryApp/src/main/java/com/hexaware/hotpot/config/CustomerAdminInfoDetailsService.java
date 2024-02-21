package com.hexaware.hotpot.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.hexaware.hotpot.entities.Administrator;
import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.repository.AdministratorRepository;
import com.hexaware.hotpot.repository.CustomersRepository;

@Component
public class CustomerAdminInfoDetailsService implements UserDetailsService {

 

	@Autowired
	CustomersRepository customerRepo;
	
	@Autowired
	AdministratorRepository adminRepo;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<UserInfo> userInfo = repository.findByName(username);
//        return userInfo.map(UserInfoUserDetails::new)
//                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    	
    	 // Check if the username exists in the customer table
        Optional<Customers> customer = customerRepo.findByUsername(username);
        if (customer.isPresent()) {
        	 String role = customer.get().getRole();
             Long customerId = customer.get().getCustId(); // Assuming you have a getId method in Customers entity
            return new CustomerInfoDetails(customer.get(), role, customerId);
        }

        // Check if the username exists in the admin table
        Optional<Administrator> admin = adminRepo.findByUsername(username);
        if (admin.isPresent()) {
        	String role = admin.get().getRole();
            Long adminId = (long) admin.get().getAdminId();
            return new AdminInfoDetails(admin.get(),role,adminId);
        }

        // If the username doesn't exist in either table, throw an exception
        throw new UsernameNotFoundException("User not found with username: " + username);

    }
    

}


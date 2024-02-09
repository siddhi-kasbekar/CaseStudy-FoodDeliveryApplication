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
            return new CustomerInfoDetails(customer.get());
        }

        // Check if the username exists in the admin table
        Optional<Administrator> admin = adminRepo.findByUserName(username);
        if (admin.isPresent()) {
            return new AdminInfoDetails(admin.get());
        }

        // If the username doesn't exist in either table, throw an exception
        throw new UsernameNotFoundException("User not found with username: " + username);

    }
    

}


package com.hexaware.hotpot.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.hotpot.dto.AdminDTO;
import com.hexaware.hotpot.dto.AuthRequest;
import com.hexaware.hotpot.dto.CustomersDTO;
import com.hexaware.hotpot.dto.DiscountDTO;
import com.hexaware.hotpot.dto.RestaurantsDTO;
import com.hexaware.hotpot.entities.Administrator;
import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.Discount;
import com.hexaware.hotpot.entities.MenuCategory;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Restaurants;
import com.hexaware.hotpot.exception.CustomerNotFoundException;
import com.hexaware.hotpot.exception.RestaurantNotFoundException;
import com.hexaware.hotpot.repository.AdministratorRepository;
import com.hexaware.hotpot.services.IAdminService;
import com.hexaware.hotpot.services.IRestaurantService;
import com.hexaware.hotpot.services.JwtService;

import jakarta.validation.Valid;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/v1/admin")
public class AdminRestController {

	private static final Logger log = LoggerFactory.getLogger(AdminRestController.class);

	@Autowired
	private IAdminService adminservice;

	@Autowired
	JwtService jwtService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	AdministratorRepository adminRepo;
	
	@Autowired
	IRestaurantService restaurantService;

	@PostMapping("/login/authenticate")

	public String authenticateAndGetTokent(@RequestBody AuthRequest authRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		String token = null;

		if (authentication.isAuthenticated()) {

			
			Optional<Administrator> admin = adminRepo.findByUsername(authRequest.getUsername());

			if (admin.isPresent()) {
				String role = admin.get().getRole();
				Long adminId = (long) admin.get().getAdminId(); 
																

				// Call generateToken method from JwtService class with additional parameters
				token = jwtService.generateToken(authRequest.getUsername(), role, adminId);

				log.info( token);
			} else {
				log.error("Admin not found in the database");
				
			}

		} else {

			log.info("invalid");

			throw new UsernameNotFoundException("UserName or Password in Invalid / Invalid Request");

		}

		return token;

	}

	@PostMapping("/register")
	@PreAuthorize("hasAuthority('admin')")

	public String registerAdmin(@RequestBody AdminDTO adminDTO) {
		long adminId = adminservice.registerManager(adminDTO);

		if (adminId != 0) {
			return "manager added successfully ";
		} else {
			return "failed to add manager ";
		}
	}

	@PostMapping("/add-restaurant")
	@PreAuthorize("hasAuthority('admin')")
	public String addRestaurant(@RequestBody @Valid RestaurantsDTO restaurantDTO) {
		Restaurants restaurant = adminservice.addRestaurant(restaurantDTO);
		if (restaurant != null) {
			return "Restaurant added successfully";
		} else {
			return "Failed to add restaurant";
		}
	}

	@PutMapping("/update-restaurant/{restaurantId}")
	@PreAuthorize("hasAuthority('admin')")
	public String updateRestaurant(@RequestBody RestaurantsDTO restaurantDTO, @PathVariable int restaurantId)
			throws RestaurantNotFoundException {

		adminservice.updateRestaurant(restaurantDTO, restaurantId);
		return "Restaurant updated successfully!";

	}

	@DeleteMapping("/removeRestaurant/{restaurantId}")
	@PreAuthorize("hasAuthority('admin')")
	public String removeRestaurant(@PathVariable Integer restaurantId) {
		adminservice.removeRestaurant(restaurantId);
		return "Restaurant removed successfully";
	}

	@DeleteMapping("/removeCustomer/{customerId}")
	@PreAuthorize("hasAuthority('admin')")
	public String removeCustomer(@PathVariable long customerId) {
		adminservice.removeCustomer(customerId);
		return "Customer removed successfully";
	}

	@GetMapping("/getAllMenus")
	@PreAuthorize("hasAuthority('admin') or hasAuthority('manager')")
	public List<MenuItems> getAllMenus() {
		return adminservice.getAllMenus();
	}

	
	@GetMapping("/getAllMenusForManager/{adminId}")
	@PreAuthorize("hasAuthority('manager')")
	public List<MenuItems> getAllMenusFormanager(@PathVariable  int adminId) {
		return adminservice.getAllMenusForManager(adminId);
	}
	
	@GetMapping("/getAllCategoriesForManager/{adminId}")
	@PreAuthorize("hasAuthority('manager')")
	public List<MenuCategory> getAllCategoriesFormanager(@PathVariable  int adminId) {
		return adminservice.getAllCategoriesForManager(adminId);
	}
	
	
	
	@GetMapping("/getAllRestaurants")
	@PreAuthorize("hasAuthority('admin') or hasAuthority('manager') or hasAuthority('customer') ")
	public List<Restaurants> getAllRestaurants() {
		return adminservice.getAllRestaurants();
	}
	
	@GetMapping("/getAllRestaurants/{adminId}")
	@PreAuthorize("hasAuthority('manager') ")
	public List<Restaurants> getAllRestaurantsByManager( @PathVariable int adminId ) {
		return restaurantService.getRestaurantsByAdminId(adminId);
	}

	@GetMapping("/getAllOrders/{adminId}")
	@PreAuthorize("hasAuthority('manager')")
	public List<Object[]> getAllOrders( @PathVariable  int adminId) {
		return adminservice.getAllOrders(adminId);
	}

	@GetMapping("/getAllCustomers")
	@PreAuthorize("hasAuthority('admin') or hasAuthority('manager')")
	public List<Customers> getAllCustomers() {
		return adminservice.getAllCustomers();
	}

	@PostMapping("/add-discount")
	@PreAuthorize("hasAuthority('admin') or hasAuthority('manager')")
	public String addDiscount(@RequestBody @Valid DiscountDTO discountDTO) {
		Discount discount = adminservice.addDiscount(discountDTO);
		if (discount != null) {
			return "Discount added successfully";
		} else {
			return "Discount to add restaurant";
		}
	}

	@DeleteMapping("/removeDiscount/{discountId}")
	@PreAuthorize("hasAuthority('admin') or hasAuthority('manager')")
	public String removeDiscount(@PathVariable int discountId) {
		adminservice.removeDiscount(discountId);
		return "Discount removed successfully";
	}

	@GetMapping("/getAllDiscounts")
	@PreAuthorize("hasAuthority('admin') or hasAuthority('manager')")
	public List<Discount> getAllDiscounts() {
		return adminservice.getAllDiscounts();
	}

	@GetMapping("/getAllManagers")
	@PreAuthorize("hasAuthority('admin')")
	public List<Administrator> getAllManagers() {
		return adminservice.getAllManagers();
	}

	@DeleteMapping("/removeManager/{managerId}")
	@PreAuthorize("hasAuthority('admin')")
	public String removeManager(@PathVariable Integer managerId) {
		adminservice.removeManager(managerId);
		return "Manager removed successfully";
	}

	@PutMapping("/update-info/{adminId}")
    @PreAuthorize("hasAuthority('manager')")
	public long updateManager(@PathVariable long adminId,  @RequestBody AdminDTO updatedAdminDTO) {

		
		return adminservice.updateManager(adminId, updatedAdminDTO);
		
	}
	
	@GetMapping("/getById/{adminId}")
	@PreAuthorize("hasAuthority('manager')")
	public Administrator getManagerById(@PathVariable long adminId) {
		return adminservice.getManagerById(adminId);
		
	}
}

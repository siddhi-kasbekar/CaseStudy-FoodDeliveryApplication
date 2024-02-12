package com.hexaware.hotpot.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.hotpot.dto.AdminDTO;
import com.hexaware.hotpot.dto.DiscountDTO;
import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.dto.RestaurantsDTO;
import com.hexaware.hotpot.entities.Administrator;
import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.Discount;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.entities.Restaurants;
import com.hexaware.hotpot.repository.AdministratorRepository;
import com.hexaware.hotpot.repository.CustomersRepository;
import com.hexaware.hotpot.repository.DiscountRepository;
import com.hexaware.hotpot.repository.MenuItemsRepository;
import com.hexaware.hotpot.repository.OrdersRepository;
import com.hexaware.hotpot.repository.RestaurantsRepository;

import jakarta.transaction.Transactional;

/*
 * Author name:Siddhi kasbekar
 * 
 * Class Description:contains business logic and functionalities related to administrator and also handles crud operations.
 * 
 */



@Service
@Transactional
public class AdminServiceImp implements IAdminService {

	@Autowired
    private RestaurantsRepository restaurantRepository;
	
	@Autowired
    private MenuItemsRepository menuItemsRepository;

    @Autowired
    private OrdersRepository orderRepository;

    @Autowired
    private CustomersRepository customersRepository;
    
    @Autowired
    private DiscountRepository discountRepo;
    
    @Autowired
    private AdministratorRepository adminRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImp.class);

	
	@Override
	public String adminLogin(AdminDTO admindto) {
		
		return null;
	}

	@Override
	public Restaurants addRestaurant(RestaurantsDTO restaurant) {
		Restaurants res = new Restaurants();
//		res.setRestaurantId(restaurant.getRestaurantId());
		res.setName(restaurant.getName());
		res.setLocation(restaurant.getLocation());
		res.setContactNumber(restaurant.getContactNumber());
		res.setRating(restaurant.getRating());		
		
		return restaurantRepository.save(res);
	}

	@Override
	public void removeRestaurant(Integer restaurantId) {
		restaurantRepository.deleteById(restaurantId);
	
	}

	@Override
	public List<MenuItems> getAllMenus() {
		
		return menuItemsRepository.findAll();
	}

	@Override
	public List<Restaurants> getAllRestaurants() {
		
		return restaurantRepository.findAll();
	}

	@Override
	public List<Orders> getAllOrders() {
		
		return orderRepository.findAll();
	}
	
	@Override
	public List<Customers> getAllCustomers() {
		return customersRepository.findAll();
	}

	

	@Override
	public MenuItems addMenuItem(MenuItemsDTO menuItemDTO, int restaurantId) {
	    Optional<Restaurants> restaurantOptional = restaurantRepository.findById(restaurantId);
	    
	    MenuItems menuItem = new MenuItems();
//        menuItem.setMenuitemId(menuItemDTO.getMenuItemId());
        menuItem.setItemName(menuItemDTO.getItemName());
        menuItem.setDescription(menuItemDTO.getDescription());
        menuItem.setCategory(menuItemDTO.getCategory());
        menuItem.setPrice(menuItemDTO.getPrice());
        menuItem.setAvailabilityTime(menuItemDTO.getAvailabilityTime());
        menuItem.setSpecialDietaryInfo(menuItemDTO.getSpecialDietaryInfo());
        menuItem.setTasteInfo(menuItemDTO.getTasteInfo());
        menuItem.setNutritionalInfo(menuItemDTO.getNutritionalInfo());
        menuItem.setCookingTime(menuItemDTO.getCookingTime());

	    if (restaurantOptional.isPresent()) {
	        Restaurants restaurant = restaurantOptional.get();       

	        menuItemDTO.setRestaurantId(restaurantId);
	        menuItem.setRestaurant(restaurant);

	        
	    } else {
	        
	    	logger.info("restaurant with specified id not found ");
	    }
	    return menuItemsRepository.save(menuItem);
	}


	

	@Override
	public void removeMenuItems(Long menuItemId) {
		menuItemsRepository.deleteById(menuItemId);
		
	}


	@Override
	public Discount addDiscount(DiscountDTO discountdto) {

		Discount discount = new Discount();
		discount.setDiscountPercentage(discountdto.getDiscountPercentage());
		discount.setStartDate(discountdto.getStartDate());
		discount.setEndDate(discountdto.getEndDate());

		return discountRepo.save(discount);
	}

	@Override
	public void removeDiscount(int discountId) {

		discountRepo.deleteById(discountId);
	}

	@Override
	public long registerManager(AdminDTO adminDTO) {
		
		Administrator admin = new Administrator();
//		admin.setAdminId(adminDTO.getAdminId());
		admin.setUserName(adminDTO.getUserName());
		admin.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
		admin.setName(adminDTO.getName());
		admin.setEmail(adminDTO.getEmail());
		admin.setRole("manager");
		
		adminRepo.save(admin);

		return admin.getAdminId();
	}

//	@Override
//	public long registerAdmin(AdminDTO adminDTO) {
//		
//		Administrator admin = new Administrator();
////		admin.setAdminId(adminDTO.getAdminId());
//		admin.setUserName(adminDTO.getUserName());
//		admin.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
//		admin.setName(adminDTO.getName());
//		admin.setEmail(adminDTO.getEmail());
//		admin.setRole("admin");
//		
//		adminRepo.save(admin);
//
//		return admin.getAdminId();
//	}

	

}
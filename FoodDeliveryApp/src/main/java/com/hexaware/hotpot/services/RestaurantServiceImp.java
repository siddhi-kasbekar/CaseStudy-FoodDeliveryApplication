package com.hexaware.hotpot.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.dto.RestaurantsDTO;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.OrderDetails;
import com.hexaware.hotpot.entities.Restaurants;
import com.hexaware.hotpot.exception.RestaurantNotFoundException;
import com.hexaware.hotpot.repository.MenuItemsRepository;
import com.hexaware.hotpot.repository.OrderDetailsRepository;
import com.hexaware.hotpot.repository.RestaurantsRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RestaurantServiceImp implements IRestaurantService {

	@Autowired
	RestaurantsRepository restaurantRepo;

	@Autowired
	MenuItemsRepository menuItemRepo;
	
	@Autowired
	OrderDetailsRepository orderDetailsrepo;

	private static final Logger logger = LoggerFactory.getLogger(RestaurantServiceImp.class);

	@Override
	public Restaurants registerRestaurant(RestaurantsDTO restaurant) {

		logger.info(restaurant + " is registered successfully!");

		Restaurants res = new Restaurants();
		res.setRestaurantId(restaurant.getRestaurantId());
		res.setName(restaurant.getName());
		res.setLocation(restaurant.getLocation());
		res.setContactNumber(restaurant.getContactNumber());

		res.setRating(restaurant.getRating());

		return restaurantRepo.save(res);
	}

	@Override
	public String loginRestaurant(RestaurantsDTO restaurantDTO) {
		// login logic
		return "login successful";
	}

	@Override
	public void addMenu(MenuItemsDTO menuItemDTO, int restaurantId) {
		Optional<Restaurants> restaurantOptional = restaurantRepo.findById(restaurantId);

		MenuItems menuItem = new MenuItems();
		menuItem.setMenuitemId(menuItemDTO.getMenuitemId());
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

			menuItem = menuItemRepo.save(menuItem);
			logger.info("Menu Item added to the menu successfully!");
			
			 
		} else {

			logger.info("restaurant with specified id not found ");
		}
		
		
		
	}

	@Override
	public void updateMenu(MenuItemsDTO menuDTO) {
		Optional<MenuItems> existingMenuItemOptional = menuItemRepo.findById(menuDTO.getMenuitemId());

		if (existingMenuItemOptional.isPresent()) {
			MenuItems existingMenuItem = existingMenuItemOptional.get();
			existingMenuItem.setItemName(menuDTO.getItemName());
			existingMenuItem.setDescription(menuDTO.getDescription());
			existingMenuItem.setCategory(menuDTO.getCategory());
			existingMenuItem.setPrice(menuDTO.getPrice());
			existingMenuItem.setAvailabilityTime(menuDTO.getAvailabilityTime());
			existingMenuItem.setSpecialDietaryInfo(menuDTO.getSpecialDietaryInfo());
			existingMenuItem.setTasteInfo(menuDTO.getTasteInfo());
			existingMenuItem.setNutritionalInfo(menuDTO.getNutritionalInfo());
			existingMenuItem.setCookingTime(menuDTO.getCookingTime());

			menuItemRepo.save(existingMenuItem);
		
			logger.info("Menu item updated in the menu successfully!");
		
		}

	}

	@Override
	public void deleteMenu(int menuId) {
        
		logger.info("Menu Item deleted successfully!");
		
		restaurantRepo.deleteById(menuId);
	}

	@Override
	public List<MenuItems> getMenuByCategory(String category) {

		return menuItemRepo.findByCategory(category);
	}

	@Override
	public List<MenuItems> getOrdersByRestaurantId(Restaurants restaurant) {

		return null;// mrepo.findByRestaurantId(restaurant);
	}

	@Override
	public List<Restaurants> searchRestaurants(String keyword) throws RestaurantNotFoundException {

		return restaurantRepo.findByNameContainingIgnoreCase(keyword);
	}

	@Override
	public List<Restaurants> searchByLocation(String location) {

		return restaurantRepo.findByLocation(location);
	}

}

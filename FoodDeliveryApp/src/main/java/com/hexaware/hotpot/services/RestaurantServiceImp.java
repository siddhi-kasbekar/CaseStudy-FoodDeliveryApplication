package com.hexaware.hotpot.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.dto.RestaurantsDTO;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Restaurants;
import com.hexaware.hotpot.exception.LocationNotFoundException;
import com.hexaware.hotpot.exception.MenuItemNotFoundException;
import com.hexaware.hotpot.exception.RestaurantNotFoundException;
import com.hexaware.hotpot.repository.MenuItemsRepository;
import com.hexaware.hotpot.repository.OrderDetailsRepository;
import com.hexaware.hotpot.repository.RestaurantsRepository;

import jakarta.transaction.Transactional;

/*
 * Author name:NipurnaBandi
 * 
 * Class Description:contains business logic and functionalities related to Restaurants and also handles crud operations.
 * 
 */

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

		logger.info("restaurant is registered successfully!");


		Restaurants res = new Restaurants();
//		res.setRestaurantId(restaurant.getRestaurantId());
		res.setName(restaurant.getName());
		res.setLocation(restaurant.getLocation());
		res.setContactNumber(restaurant.getContactNumber());

		res.setRating(restaurant.getRating());

		return restaurantRepo.save(res);
	}

	

	@Override
	public MenuItems addMenu(MenuItemsDTO menuItemDTO, int restaurantId) {
		Optional<Restaurants> restaurantOptional = restaurantRepo.findById(restaurantId);

		MenuItems menuItem = new MenuItems();
//		menuItem.setMenuitemId(menuItemDTO.getMenuItemId());
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

			 menuItemRepo.save(menuItem);
			logger.info("Menu Item added to the menu successfully!");

		} else {

			logger.info("restaurant with the searched id not found ");
		}
		return menuItem;

	}

	@Override
	public void updateMenu(MenuItemsDTO menuDTO) throws MenuItemNotFoundException {
	    // Retrieve the existing menu item entity from the database
	    MenuItems existingMenuItem = menuItemRepo.findById(menuDTO.getMenuItemId())
	            .orElseThrow(() -> new MenuItemNotFoundException("Menu item not found with ID: " + menuDTO.getMenuItemId()));

	    // Update the menu item entity with the new values provided in the DTO
	    existingMenuItem.setItemName(Objects.requireNonNullElse(menuDTO.getItemName(), existingMenuItem.getItemName()));
	    existingMenuItem.setDescription(Objects.requireNonNullElse(menuDTO.getDescription(), existingMenuItem.getDescription()));
	    existingMenuItem.setCategory(Objects.requireNonNullElse(menuDTO.getCategory(), existingMenuItem.getCategory()));
	    existingMenuItem.setPrice(Objects.requireNonNullElse(menuDTO.getPrice(), existingMenuItem.getPrice()));
	    existingMenuItem.setAvailabilityTime(Objects.requireNonNullElse(menuDTO.getAvailabilityTime(), existingMenuItem.getAvailabilityTime()));
	    existingMenuItem.setSpecialDietaryInfo(Objects.requireNonNullElse(menuDTO.getSpecialDietaryInfo(), existingMenuItem.getSpecialDietaryInfo()));
	    existingMenuItem.setTasteInfo(Objects.requireNonNullElse(menuDTO.getTasteInfo(), existingMenuItem.getTasteInfo()));
	    existingMenuItem.setNutritionalInfo(Objects.requireNonNullElse(menuDTO.getNutritionalInfo(), existingMenuItem.getNutritionalInfo()));
	    existingMenuItem.setCookingTime(Objects.requireNonNullElse(menuDTO.getCookingTime(), existingMenuItem.getCookingTime()));

	    // Save the updated menu item entity
	    menuItemRepo.save(existingMenuItem);

	    logger.info("Menu item updated in the menu successfully!");
	}


	@Override
	public void deleteMenu(int menuId) {

		logger.info("Menu Item deleted successfully!");

		restaurantRepo.deleteById(menuId);
	}

	@Override
	public List<MenuItems> getMenuByCategory(String category) throws MenuItemNotFoundException {

		List<MenuItems> menuItem = menuItemRepo.findByCategory(category);
		if (menuItem.isEmpty()) {
			throw new MenuItemNotFoundException("Error retrieving menu items for category: " + category);
		}
		
		return menuItem;
	}

	

	@Override
	public List<Restaurants> searchRestaurants(String keyword) throws RestaurantNotFoundException {
		List<Restaurants> restaurants = restaurantRepo.findByNameContainingIgnoreCase(keyword);
		if (restaurants.isEmpty()) {
			throw new RestaurantNotFoundException("Error retrieving restaurants for keyword: " + keyword);
		}
		return restaurants;
	}

	@Override
	public List<Restaurants> searchByLocation(String location) throws LocationNotFoundException {
		List<Restaurants> restaurants = restaurantRepo.findByLocation(location);

		if (restaurants.isEmpty()) {
			throw new LocationNotFoundException("No restaurants found in location: " + location);
		}

		return restaurants;
	}

}

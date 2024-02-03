package com.hexaware.hotpot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.dto.RestaurantsDTO;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Restaurants;
import com.hexaware.hotpot.repository.MenuItemsRepository;
import com.hexaware.hotpot.repository.RestaurantsRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class RestaurantServiceImp implements IRestaurantService {

	@Autowired
	RestaurantsRepository repo;
	
	@Autowired
	MenuItemsRepository mrepo;
	
	@Override
	public int registerRestaurant(RestaurantsDTO restaurantDTO) {
		
		Restaurants restaurant = new Restaurants();
        restaurant.setName(restaurantDTO.getName());
        restaurant.setLocation(restaurantDTO.getLocation());
        restaurant.setContactNumber(restaurantDTO.getContactNumber());
        

        Restaurants savedRestaurant = repo.save(restaurant);
        return savedRestaurant.getRestaurantId();
	}

	@Override
	public String loginRestaurant(RestaurantsDTO restaurantDTO) {
		//login logic
		return "login successful";
	}

	@Override
	public void addMenu(MenuItemsDTO menuDTO) {
		Integer restaurantId = menuDTO.getRestaurantId(); 
	    Optional<Restaurants> restaurantOptional = repo.findById(restaurantId);

	    if (restaurantOptional.isPresent()) {
	        Restaurants restaurant = restaurantOptional.get();

	        MenuItems menuItem = new MenuItems();
	        menuItem.setMenuitemId(menuDTO.getMenuitemId());
	        menuItem.setItemName(menuDTO.getItemName());
	        menuItem.setDescription(menuDTO.getDescription());
	        menuItem.setCategory(menuDTO.getCategory());
	        menuItem.setPrice(menuDTO.getPrice());
	        menuItem.setAvailabilityTime(menuDTO.getAvailabilityTime());
	        menuItem.setSpecialDietaryInfo(menuDTO.getSpecialDietaryInfo());
	        menuItem.setTasteInfo(menuDTO.getTasteInfo());
	        menuItem.setNutritionalInfo(menuDTO.getNutritionalInfo());
	        menuItem.setCookingTime(menuDTO.getCookingTime());

	        // Set the associated restaurant
	        menuItem.setRestaurant(restaurant);
	        mrepo.save(menuItem);
	        // Save the menu item
	    }
		}

	

	@Override
	public void updateMenu(MenuItemsDTO menuDTO) {
		Optional<MenuItems> existingMenuItemOptional = mrepo.findById(menuDTO.getMenuitemId());

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

	        mrepo.save(existingMenuItem);}

	}

	@Override
	public void deleteMenu(Long menuId) {
		
     repo.deleteById(menuId);
	}

	@Override
	public List<MenuItems> getMenuByCategory(String category) {
		
		 return mrepo.findByMenuItemsCategory(category);
	}

	@Override
	public List<MenuItems> getOrdersByRestaurantId(Restaurants restaurant) {
		
		return mrepo.findByMenuItemsRestaurantId(restaurant);
	}

}

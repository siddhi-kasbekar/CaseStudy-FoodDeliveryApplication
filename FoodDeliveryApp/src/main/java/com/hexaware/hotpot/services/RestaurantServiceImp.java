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
	public Restaurants registerRestaurant(RestaurantsDTO restaurant) {
		Restaurants res = new Restaurants();
		res.setRestaurantId(restaurant.getRestaurantId());
		res.setName(restaurant.getName());
		res.setLocation(restaurant.getLocation());
		res.setContactNumber(restaurant.getContactNumber());
		
	    res.setRating(restaurant.getRating());
		
		
		return repo.save(res);
	}

	@Override
	public String loginRestaurant(RestaurantsDTO restaurantDTO) {
		//login logic
		return "login successful";
	}

	
	@Override
	public MenuItems addMenu(MenuItemsDTO menuItemDTO,int restaurantId){
	    Optional<Restaurants> restaurantOptional = repo.findById(restaurantId);
	    
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

	        
	    } else {
	        
	    	System.out.println("restaurant with specified id not found ");
	    }
	    return mrepo.save(menuItem);
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
	public void deleteMenu(int menuId) {
		
     repo.deleteById(menuId);
	}

	@Override
	public List<MenuItems> getMenuByCategory(String category) {
		
		 return mrepo.findByCategory( category);
	}

	@Override
	public List<MenuItems> getOrdersByRestaurantId(Restaurants restaurant) {
		
		return null;// mrepo.findByRestaurantId(restaurant);
	}

	@Override
	public List<Restaurants> searchRestaurants(String keyword) {
		
		return repo.findByNameContainingIgnoreCase(keyword);
	}

}

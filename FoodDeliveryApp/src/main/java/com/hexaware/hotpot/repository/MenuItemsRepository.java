package com.hexaware.hotpot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Restaurants;

@Repository
public interface MenuItemsRepository extends JpaRepository<MenuItems, Long> {

	

    List<MenuItems> findByItemNameContainingIgnoreCaseAndRestaurantRestaurantId(String keyword, int restaurantId);
	
    List<MenuItems> findByRestaurantRestaurantId(int restaurantId);

	

	List<MenuItems> findByCategoryAndRestaurantRestaurantId(String category, int restaurantId);

	List<MenuItems> findByRestaurantRestaurantIdAndPriceBetween( int restaurantId, double minPrice, double maxPrice);
	
 List<MenuItems> findByRestaurantRestaurantIdAndSpecialDietaryInfo(int restaurantId, String dietaryInfo);
	//List<MenuItems> findByRestaurantId(Restaurants restaurant);

	

	//List<MenuItems> findByMenuItemsCategory(String category);

	//List<MenuItems> findByMenuItemsRestaurantId(int restaurantId);
	//List<MenuItems> findByMenuItemsRestaurantId(Restaurants restaurant);

}

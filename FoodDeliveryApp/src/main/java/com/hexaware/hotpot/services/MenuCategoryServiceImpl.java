package com.hexaware.hotpot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.hotpot.dto.MenuCategoryDTO;
import com.hexaware.hotpot.entities.MenuCategory;
import com.hexaware.hotpot.entities.Restaurants;
import com.hexaware.hotpot.repository.MenuCategoryRepository;
import com.hexaware.hotpot.repository.RestaurantsRepository;

@Service
public class MenuCategoryServiceImpl implements IMenuCategoryService {

	@Autowired
	MenuCategoryRepository repo;
	
	@Autowired
	RestaurantsRepository restaurantRepo;
	
	@Override
	public MenuCategory createCategory(MenuCategoryDTO categoryDTO, int restaurantId) {
	    Optional<Restaurants> restaurantOptional = restaurantRepo.findById(restaurantId);

	    MenuCategory category = new MenuCategory();

	    category.setCategoryName(categoryDTO.getCategoryName());
	    
	   

	    if (restaurantOptional.isPresent()) {
	        Restaurants restaurant = restaurantOptional.get();

	        category.setRestaurant(restaurant);

	        repo.save(category);
//	        logger.info("Category added successfully!");

	    } else {
//	        logger.info("Restaurant with the searched id not found");
	    }
	    return category;
	}


	@Override
	public MenuCategory getCategoryById(int categoryId) {
		
		return repo.getById(categoryId);
	}

	@Override
	public MenuCategory updateCategory(MenuCategory category) {
		
		return repo.save(category);
	}

	@Override
	public void deleteCategory(int categoryId) {
		repo.deleteById(categoryId);

	}

	

}

package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.dto.MenuCategoryDTO;
import com.hexaware.hotpot.entities.MenuCategory;

public interface IMenuCategoryService {

	MenuCategory createCategory(MenuCategoryDTO categoryDTO, int restaurantId);

	List <MenuCategory> getCategoryByRestaurantId(int restaurantId);

	MenuCategory updateCategory(MenuCategory category);

	void deleteCategory(int categoryId);
	
	
}

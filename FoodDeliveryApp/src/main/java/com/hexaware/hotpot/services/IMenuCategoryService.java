package com.hexaware.hotpot.services;

import com.hexaware.hotpot.dto.MenuCategoryDTO;
import com.hexaware.hotpot.entities.MenuCategory;

public interface IMenuCategoryService {

	MenuCategory createCategory(MenuCategoryDTO categoryDTO, int restaurantId);

	MenuCategory getCategoryById(int categoryId);

	MenuCategory updateCategory(MenuCategory category);

	void deleteCategory(int categoryId);
	
	
}

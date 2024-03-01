package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.entities.MenuCategory;

public interface IMenuCategoryService {

	MenuCategory createCategory(MenuCategory category);

	MenuCategory getCategoryById(int categoryId);

	MenuCategory updateCategory(MenuCategory category);

	void deleteCategory(int categoryId);
	
	
}

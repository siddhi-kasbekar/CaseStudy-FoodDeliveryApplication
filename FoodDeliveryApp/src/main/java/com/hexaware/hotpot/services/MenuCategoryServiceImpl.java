package com.hexaware.hotpot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.hotpot.entities.MenuCategory;
import com.hexaware.hotpot.repository.MenuCategoryRepository;

@Service
public class MenuCategoryServiceImpl implements IMenuCategoryService {

	@Autowired
	MenuCategoryRepository repo;
	
	@Override
	public MenuCategory createCategory(MenuCategory category) {
		
		return repo.save(category);
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

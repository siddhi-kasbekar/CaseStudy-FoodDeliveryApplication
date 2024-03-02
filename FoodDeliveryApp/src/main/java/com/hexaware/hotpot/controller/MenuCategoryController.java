package com.hexaware.hotpot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.hotpot.dto.MenuCategoryDTO;
import com.hexaware.hotpot.entities.MenuCategory;
import com.hexaware.hotpot.services.IMenuCategoryService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/v1/menuCategory")
public class MenuCategoryController {

	@Autowired
	IMenuCategoryService service;
	
	@PostMapping("/create-category/{restaurantId}")
	@PreAuthorize("hasAuthority('manager')")
	MenuCategory createCategory(@RequestBody MenuCategoryDTO categoryDTO,@PathVariable int restaurantId) {
	      return service.createCategory(categoryDTO,restaurantId);
	}

	@GetMapping("/get-category/{restaurantId}")
	@PreAuthorize("hasAuthority('customer')")
	List<MenuCategory> getCategoryByRestaurantId(@PathVariable int restaurantId) {
		return service.getCategoryByRestaurantId(restaurantId);
	}

	@PostMapping("/update-category")
	@PreAuthorize("hasAuthority('manager')")
	MenuCategory updateCategory(@RequestBody MenuCategory category) {
		return service.updateCategory(category);
	}

	@DeleteMapping("/delete-category/{categoryId}")
	@PreAuthorize("hasAuthority('manager')")
	String deleteCategory(@PathVariable int categoryId) {
		service.deleteCategory(categoryId);
		return "Category deleted successfully ";
	}

	
}

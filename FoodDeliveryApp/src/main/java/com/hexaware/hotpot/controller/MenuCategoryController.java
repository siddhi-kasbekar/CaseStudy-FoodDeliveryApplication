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

import com.hexaware.hotpot.entities.MenuCategory;
import com.hexaware.hotpot.services.IMenuCategoryService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/v1/menuCategory")
public class MenuCategoryController {

	@Autowired
	IMenuCategoryService service;
	
	@PostMapping("/create-category")
	@PreAuthorize("hasAuthority('manager')")
	MenuCategory createCategory(@RequestBody MenuCategory category) {
	      return service.createCategory(category);
	}

	@GetMapping("/get-category/{categoryId}")
	@PreAuthorize("hasAuthority('manager')")
	MenuCategory getCategoryById(@PathVariable int categoryId) {
		return service.getCategoryById(categoryId);
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

package com.hexaware.hotpot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;

@Entity
public class MenuCategory {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_category_id_sequence")
	    @SequenceGenerator(name = "menu_category_id_sequence", sequenceName = "MENU_CATEGORY_ID_SEQUENCE", initialValue = 701, allocationSize = 1)
	    @Column(name = "CategoryId")
	    private int categoryId;

	    @NotNull(message = "Category name is required")
	    private String categoryName;

	    @JsonIgnore
	    @ManyToOne
	    @JoinColumn(name = "restaurantId", nullable = true)
	    private Restaurants restaurant;

	    public MenuCategory() {
	        super();
	    }

	    public MenuCategory(String categoryName, Restaurants restaurant) {
	        this.categoryName = categoryName;
	        this.restaurant = restaurant;
	    }



	    public int getCategoryId() {
	        return categoryId;
	    }

	    public void setCategoryId(int categoryId) {
	        this.categoryId = categoryId;
	    }

	    public String getCategoryName() {
	        return categoryName;
	    }

	    public void setCategoryName(String categoryName) {
	        this.categoryName = categoryName;
	    }

	    public Restaurants getRestaurant() {
	        return restaurant;
	    }

	    public void setRestaurant(Restaurants restaurant) {
	        this.restaurant = restaurant;
	    }

}

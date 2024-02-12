package com.hexaware.hotpot.dto;

public class MenuItemsDTO {

	private long menuItemId;
    private String itemName;
    private String description;
    private String category;
   
	private double price;


	private String availabilityTime;


	private String specialDietaryInfo;

	private String tasteInfo;

	private String nutritionalInfo;

	private int cookingTime;
	
	private int restaurantId;
	
	private int quantity;

	public MenuItemsDTO() {
		super();
	}


	public MenuItemsDTO(long menuItemId, String itemName, String description, String category, double price,
			String availabilityTime, String specialDietaryInfo, String tasteInfo, String nutritionalInfo,
			int cookingTime,int quantity) {
		super();
		this.menuItemId = menuItemId;
		this.itemName = itemName;
		this.description = description;
		this.category = category;
		this.price = price;
		this.availabilityTime = availabilityTime;
		this.specialDietaryInfo = specialDietaryInfo;
		this.tasteInfo = tasteInfo;
		this.nutritionalInfo = nutritionalInfo;
		this.cookingTime = cookingTime;
		this.quantity=quantity;}
		

	public long getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(long menuitemId) {
		this.menuItemId = menuitemId;
	}

	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAvailabilityTime() {
		return availabilityTime;
	}

	public void setAvailabilityTime(String availabilityTime) {
		this.availabilityTime = availabilityTime;
	}

	public String getSpecialDietaryInfo() {
		return specialDietaryInfo;
	}

	public void setSpecialDietaryInfo(String specialDietaryInfo) {
		this.specialDietaryInfo = specialDietaryInfo;
	}

	public String getTasteInfo() {
		return tasteInfo;
	}

	public void setTasteInfo(String tasteInfo) {
		this.tasteInfo = tasteInfo;
	}

	public String getNutritionalInfo() {
		return nutritionalInfo;
	}

	public void setNutritionalInfo(String nutritionalInfo) {
		this.nutritionalInfo = nutritionalInfo;
	}


	public int getCookingTime() {
		return cookingTime;
	}

	public void setCookingTime(int cookingTime) {
		this.cookingTime = cookingTime;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	

	
	


	
}

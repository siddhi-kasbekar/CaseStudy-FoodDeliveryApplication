package com.hexaware.hotpot.dto;



public class MenuCategoryDTO {
	
	   private int categoryId;

	    private String categoryName;

		private int restaurantId;
		
		

		public MenuCategoryDTO() {
			super();
		}
		
		

		public MenuCategoryDTO(int categoryId, String categoryName, int restaurantId) {
			super();
			this.categoryId = categoryId;
			this.categoryName = categoryName;
			this.restaurantId = restaurantId;
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

		public int getRestaurantId() {
			return restaurantId;
		}

		public void setRestaurantId(int restaurantId) {
			this.restaurantId = restaurantId;
		}
		
		

}

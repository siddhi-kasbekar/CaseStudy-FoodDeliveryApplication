package com.hexaware.hotpot.services;

import java.util.List;

import com.hexaware.hotpot.dto.AdminDTO;
import com.hexaware.hotpot.dto.DiscountDTO;
import com.hexaware.hotpot.dto.RestaurantsDTO;
import com.hexaware.hotpot.entities.Administrator;
import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.Discount;
import com.hexaware.hotpot.entities.MenuCategory;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Restaurants;
import com.hexaware.hotpot.exception.RestaurantNotFoundException;

public interface IAdminService {

	public long registerManager(AdminDTO adminDTO);
	

	public String adminLogin(AdminDTO admindto);

	public Restaurants addRestaurant(RestaurantsDTO restaurant);

    public Restaurants updateRestaurant(RestaurantsDTO restaurant,int restaurantId)throws RestaurantNotFoundException;
	
	public void removeRestaurant(Integer restaurantId) ;
	
	public void removeCustomer(long customerId) ;
	
	public void removeManager(int managerId);



	public List<MenuItems> getAllMenus();

	public List<Restaurants> getAllRestaurants();

	public List<Object[]> getAllOrders(int adminId);

	public List<Customers> getAllCustomers();

	public Discount addDiscount(DiscountDTO discountdto);

	public void removeDiscount(int discountId);

	public List<Discount> getAllDiscounts();
	
	public List<Administrator> getAllManagers();
	
	public List<MenuItems> getAllMenusForManager(int adminId);
	
	public List<MenuCategory> getAllCategoriesForManager(int adminId);


	public Administrator getManagerById(long adminId);


	public long updateManager(long adminId, AdminDTO updatedAdminDTO);
}

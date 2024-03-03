package com.hexaware.hotpot.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.hexaware.hotpot.dto.CartDetailsDTO;
import com.hexaware.hotpot.dto.MenuItemsDTO;
import com.hexaware.hotpot.entities.Cart;
import com.hexaware.hotpot.exception.CustomerNotFoundException;
import com.hexaware.hotpot.exception.DiscountNotFoundException;
import com.hexaware.hotpot.services.ICartService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/v1/cart")
public class CartRestController {
	
    


	@Autowired
	ICartService cartService;
	
	
	@PostMapping("/save-cart/{customerId}")
	@PreAuthorize("hasAuthority('customer')")
	public Cart saveCartState(@PathVariable long customerId,  @RequestBody Map<String, Object> requestBody) throws CustomerNotFoundException {
	    List<MenuItemsDTO> menuItems = parseMenuItems(requestBody);

	    double total = (double) requestBody.get("total");

		return cartService.saveCartState(customerId, menuItems, total);
	}
	
	
@DeleteMapping("/clearAll/{customerId}")
@PreAuthorize("hasAuthority('customer')")
public String clearCart( @PathVariable  long customerId) throws CustomerNotFoundException{
	cartService.clearCart(customerId);
	return "cart cleared";
	
}
	
private List<MenuItemsDTO> parseMenuItems(Map<String, Object> requestBody) {
    List<Map<String, Object>> menuItemList = (List<Map<String, Object>>) requestBody.get("menuItems");
    List<MenuItemsDTO> menuItems = new ArrayList<>();
    for (Map<String, Object> menuItemData : menuItemList) {
        MenuItemsDTO menuItemDTO = new MenuItemsDTO();
        menuItemDTO.setMenuItemId(Long.parseLong(menuItemData.get("menuItemId").toString()));
        menuItemDTO.setQuantity(Integer.parseInt(menuItemData.get("quantity").toString()));
        menuItems.add(menuItemDTO);
    }
    return menuItems;
}


@PostMapping("/addToCart/{customerId}")
@PreAuthorize("hasAuthority('customer')")
public String addToCart(@PathVariable Long customerId,  @RequestBody CartDetailsDTO cartDetailsDTO) throws CustomerNotFoundException{
    

	cartService.addToCart(customerId,  cartDetailsDTO);
	return "added to cart";
	
	
}


@DeleteMapping("/removeFromCart/{customerId}/{menuItemId}")
@PreAuthorize("hasAuthority('customer')")
public String removeFromCart(@PathVariable Long customerId, @PathVariable Long menuItemId) {

    int fixedQuantity = 1;

    cartService.removeFromCart(customerId, menuItemId, fixedQuantity);

    return "removed from cart";
}

@GetMapping("/applyDiscount/{customerId}")
@PreAuthorize("hasAuthority('customer')")
public String applyDiscount(@PathVariable long customerId) throws DiscountNotFoundException {
    // Call the method to calculate and update the total with the discount
	LocalDate currentDate = LocalDate.now();
    cartService.calculateDiscountedTotal(customerId, currentDate);
    
    return "Discount applied successfully";
}

@GetMapping("/details/{customerId}")
@PreAuthorize("hasAuthority('customer')")
public List<CartDetailsDTO> getCartDetails(@PathVariable Long customerId) {
    List<Object[]> cartDetails = cartService.getCartDetails(customerId);

    // Process the data and convert it to a DTO
    return processCartDetails(cartDetails);
}

private List<CartDetailsDTO> processCartDetails(List<Object[]> cartDetails) {
	List<CartDetailsDTO> cartItemDetailsList = new ArrayList<>();

	double total = 0.0;  // Initialize total outside the loop

	if (!cartDetails.isEmpty()) {
	    // Assuming total is at index 1 in the result set
	    total = ((Number) cartDetails.get(0)[1]).doubleValue();
	}

	for (Object[] row : cartDetails) {
	    long menuItemId = ((Number) row[2]).longValue(); // Assuming menuItemId is at index 2
        String itemName = (String) row[3]; // Assuming item_name is at index 3
	    double price = ((Number) row[4]).doubleValue(); // Assuming price is at index 3
	    int quantity = ((Number) row[5]).intValue();    // Assuming quantity is at index 4
	    double individualTotal = ((Number) row[6]).doubleValue(); // Assuming individualTotal is at index 5

	    CartDetailsDTO cartItemDetailsDTO = new CartDetailsDTO(menuItemId, itemName,quantity, price, total, individualTotal);
	    cartItemDetailsList.add(cartItemDetailsDTO);
	}

	return cartItemDetailsList;

}




}

package com.hexaware.hotpot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler({CustomerNotFoundException.class})
	public ResponseEntity<String> handleCustomerNotFound(){
		return new ResponseEntity<String>("Customer with given id not found", HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler({LocationNotFoundException.class})
	public ResponseEntity<String> handleLocationNotFound(LocationNotFoundException ex){
		return new ResponseEntity<String>("No restaurant found in this location", HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler({OrderNotFoundException.class})
	public ResponseEntity<String> handleOrderNotFound(){
		return new ResponseEntity<String>("No order found ", HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler({MenuItemNotFoundException.class})
	public ResponseEntity<String> handleMenuItemNotFound(){
		return new ResponseEntity<String>("Menu item not found", HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler({RestaurantNotFoundException.class})
	public ResponseEntity<String> handleRestaurantNotFound(){
		return new ResponseEntity<String>("Restaurant is not found", HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler({DiscountNotFoundException.class})
	public ResponseEntity<String> handleDiscountNotFound(){
		return new ResponseEntity<String>("Discount not applied", HttpStatus.NOT_FOUND);
		
	}

}

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
	public ResponseEntity<String> handleLocationNotFound(){
		return new ResponseEntity<String>("No restaurant found in this location", HttpStatus.NOT_FOUND);
		
	}

}

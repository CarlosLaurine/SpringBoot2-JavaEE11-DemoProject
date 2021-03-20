package com.educandoweb.springBootStudies.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.springBootStudies.entities.User;

//Creating RestController that answers to the path "/users"

//Putting an annotation to signal that this class is a web resource implemented by a Rest Controller
@RestController

//Setting a name to the Resource and setting a Resource Path
@RequestMapping (value = "/users")

public class UserResource {
	
	//End Point Method to access users
	//ResponseEntity<> = Spring Specific Return Type to return Responses from Web Requests
	
	//Setting @GetMapping Annotation to indicate that the following method will respond to HTTP protocol "get" requisition
	@GetMapping
	public ResponseEntity<User> findAll() {
		
		User user = new User(1L, "john", "john@gmail.com", "99999-7777", "12345");
		
		/*Calling ResponseENTITY.ok() to successfully return the response at HTTP protocol 
		  and .body() to also return the response body with the User object user */
		
		return ResponseEntity.ok().body(user);
		
	}

}

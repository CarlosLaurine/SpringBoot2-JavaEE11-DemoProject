package com.educandoweb.springBootStudies.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.springBootStudies.entities.User;
import com.educandoweb.springBootStudies.services.UserService;

//Creating RestController that answers to the path "/users"

//Putting an annotation to signal that this class is a web resource implemented by a Rest Controller
@RestController

//Setting a name to the Resource and setting a Resource Path
@RequestMapping (value = "/users")

//Instantiating Service Layer Dependence to ensure the proper Logic Layer Architecture at the code
/*OBS: Even though sometimes the Service Layer only returns a calling to the Repositories' (DAO) Layer,
  it is still a good practice to maintain the exclusive dependence from Resource Layer with Service Layer
  instead of connecting that directly to the DAO Layer. This happens in order to maintain the properly 
  organized Logic Layer Architecture, once many times the Service Layer will have Business Rules that 
  only it will be able to perform amidst the Rest Controllers and the Data Repositories. Thus, to maintain 
  the coherence and proper division of duties, it is preferable that Resource Layer depends only on Service 
  Layer without shortcuts.
 */

public class UserResource {

	//Setting dependence to the Service Layer
	
	/*OBS: In this case it is not required to create the interface implementation,
	  since Spring Data JPA has a default implementation for this type of interface.
	  Once a Generic JpaRepository with the Entity and the Entity Id is defined
	  as being extended, a pre-existent default implementation will automatically occur */

	/*OBS2: All objects that will be injected through Spring Framework's Dependency-Injection
	  Mechanism must have its respective Classes registered at this mechanism (All frameworks 
	  in general provide some feature for this registration). In this case, UserService must have
	  the registration for this mechanism inside itself*/
	@Autowired
	private UserService userService;
	
	//End Point Method to access users
	//ResponseEntity<> = Spring Specific Return Type to return Responses from Web Requests
	
	//Setting @GetMapping Annotation to indicate that the following method will respond to HTTP protocol "get" requisition
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		
		//Connecting with User Repository' findAll() method through User Service's findAll() method
		List <User> list = userService.findAll();
		
		/*Calling ResponseENTITY.ok() to successfully return the response at HTTP protocol 
		  and .body() to also return the response body with the List of User objects in it */
		
		return ResponseEntity.ok().body(list);
		
	}

	//Setting @GetMapping Annotation to indicate that the following method will respond to HTTP protocol "get" requisition
	/*OBS: Since the value passed at the URL will not be a simple word, but the user id (which is a parameter for its variable), 
	  then the "id" word must be surrounded with brackets {}*/
	@GetMapping(value = "/{id}")
	
	/*OBS2:In order for spring to accept the id as a parameter and display it at the URL, it is required to put an annotation
	  @PathVariable right before with the id Parameter at the method signature */
	public ResponseEntity<User> findById(@PathVariable Long id) {
	
		User user = userService.findById(id);
		
		return ResponseEntity.ok().body(user);
	}
	
	//OBS:Up to now all Endpoints' mapping was based in Get methods, since their role is to just get Data from the database (READ/SELECT)

	//OBS2: From this point below, the methods will use the @PostMapping annotation based on Post Method, once they wil alter the Database Data (Update, Delete, Insert)

	@PostMapping
	
	/*To indicate that the User object will arrive as JSON through the requisition and that it will be later de-serialized as a Java User Object, the annotation @RequestBody before the User parameter at the Method is required*/
	public ResponseEntity<User> insert (@RequestBody User user){
		
		user = userService.insertUser(user);
		
		//Setting URI object containing the address from the newly inserted User Object 
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(user.getId()).toUri();
		
		
		/*using .created() instead of .ok() in order to return a HTTP Response Code 201
		  in order to indicate that a new resource was created (through insertion)*/
		
		/*
		OBS: In this case, to fulfill the .created() method constructor, a URI-typed object is needed. 
		This happens since at the HTTP Protocol when a 201 Response Code will be Returned, its response 
		must contain a header (Location) containing the address from the new inserted Resource.
		*/
		
		return ResponseEntity.created(uri).body(user);		
	}


	/*At REST protocol, the HTTP Method used to perform data deletion is the "delete", so the Springboot annotation needed for this feature is @DeleteMapping*/
	/*OBS: Since the value passed at the URL will not be a simple word, but the user id (which is a parameter for its variable), 
	  then the "id" word must be surrounded with brackets {}*/
	@DeleteMapping(value = "/{id}")
	
	/*OBS2:In order for spring to accept the id as a parameter and display it at the URL, it is required to put an annotation
	  @PathVariable right before with the id Parameter at the method signature */
	
	//OBS: ResponseEntity's generic Content will be Void, since it will have no body return
	public ResponseEntity<Void> delete(@PathVariable Long id){
		
		userService.delete(id);
		
		/*Since this is a Response without any body (Void), the ResponseEntity method to be called is ".noContent()" followed by ".build()", since the former will return an empty Response with the HTTP Code 204 (No Content Response) */
		
		return ResponseEntity.noContent().build();
	}
}

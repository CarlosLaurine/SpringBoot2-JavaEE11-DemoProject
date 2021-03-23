package com.educandoweb.springBootStudies.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.springBootStudies.entities.Order;
import com.educandoweb.springBootStudies.services.OrderService;

//Creating RestController that answers to the path "/orders"

//Putting an annotation to signal that this class is a web resource implemented by a Rest Controller
@RestController

//Setting a name to the Resource and setting a Resource Path
@RequestMapping (value = "/orders")

//Instantiating Service Layer Dependence to ensure the proper Logic Layer Architecture at the code
/*OBS: Even though sometimes the Service Layer only returns a calling to the Repositories' (DAO) Layer,
  it is still a good practice to maintain the exclusive dependence from Resource Layer with Service Layer
  instead of connecting that directly to the DAO Layer. This happens in order to maintain the properly 
  organized Logic Layer Architecture, once many times the Service Layer will have Business Rules that 
  only it will be able to perform amidst the Rest Controllers and the Data Repositories. Thus, to maintain 
  the coherence and proper division of duties, it is preferable that Resource Layer depends only on Service 
  Layer without shortcuts.
 */

public class OrderResource {

	//Setting dependence to the Service Layer
	
	/*OBS: In this case it is not required to create the interface implementation,
	  since Spring Data JPA has a default implementation for this type of interface.
	  Once a Generic JpaRepository with the Entity and the Entity Id is defined
	  as being extended, a pre-existent default implementation will automatically occur */

	/*OBS2: All objects that will be injected through Spring Framework's Dependency-Injection
	  Mechanism must have its respective Classes registered at this mechanism (All frameworks 
	  in general provide some feature for this registration). In this case, OrderService must have
	  the registration for this mechanism inside itself*/
	@Autowired
	private OrderService OrderService;
	
	//End Point Method to access Orders
	//ResponseEntity<> = Spring Specific Return Type to return Responses from Web Requests
	
	//Setting @GetMapping Annotation to indicate that the following method will respond to HTTP protocol "get" requisition
	@GetMapping
	public ResponseEntity<List<Order>> findAll() {
		
		//Connecting with Order Repository' findAll() method through Order Service's findAll() method
		List <Order> list = OrderService.findAll();
		
		/*Calling ResponseENTITY.ok() to successfully return the response at HTTP protocol 
		  and .body() to also return the response body with the List of Order objects in it */
		
		return ResponseEntity.ok().body(list);
		
	}

	//Setting @GetMapping Annotation to indicate that the following method will respond to HTTP protocol "get" requisition
	/*OBS: Since the value passed at the URL will not be a simple word, but the Order id (which is a parameter for its variable), 
	  then the "id" word must be surrounded wit brackets {}*/
	@GetMapping(value = ("/{id}"))
	
	/*OBS2:In order for spring to accept the id as a parameter and displays it at the URL, it is required to put an annotation
	  @PathVariable right before with the id Parameter at the method signature */
	public ResponseEntity<Order> findById(@PathVariable Long id) {
	
		Order order = OrderService.findById(id);
		
		return ResponseEntity.ok().body(order);
	}
}

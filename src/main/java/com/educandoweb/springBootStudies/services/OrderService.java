package com.educandoweb.springBootStudies.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.springBootStudies.entities.Order;
import com.educandoweb.springBootStudies.repositories.OrderRepository;

/*Registering the class as a Spring Component in order to make available for it the Spring's 
  Dependency-Injection Mechanism which, in this case, will be useful at declaring Order 
  Resources' Dependency to the now Registered User Services*/

/*OBS:This Registration can be made through different annotations: @Component, @Repository 
  and @Service, the first being more general and the two last ones having a more specific 
  Semantic for the various particular cases. In this case, for example, using the @Service 
  annotation was more appropriate to registering the UserService for self-explanatory reasons 
 */
@Service
public class OrderService {

	/*In order for Spring Framework to provide the required dependence definitions and associate an 
	instance of UserRepository at UserService, it is required to put the following annotation 
	above the Dependency attribute*/
	@Autowired
	private OrderRepository orderRepository;
	
	//Finding All Users/Database Table Rows
	public List<Order> findAll(){
		return orderRepository.findAll();
	}
	
	//Finding Users/Database Table Rows by IDs/Primary Keys
	public Order findById(Long id) {
		
		//OBS: the operation findByid() from JPA CRUD returns an Optional-typed object
		Optional<Order> order = orderRepository.findById(id);
		
		//Returning User type return through Optional Object Method .get()
		return order.get();
		
	}
}

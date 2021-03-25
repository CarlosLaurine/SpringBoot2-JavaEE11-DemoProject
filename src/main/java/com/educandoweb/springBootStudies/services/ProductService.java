package com.educandoweb.springBootStudies.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.springBootStudies.entities.Product;
import com.educandoweb.springBootStudies.repositories.ProductRepository;

/*Registering the class as a Spring Component in order to make it available for the Spring's 
  Dependency-Injection Mechanism which, in this case, will be useful at declaring Product 
  Resources' Dependency to the now Registered Product Services*/

/*OBS:This Registration can be made through different annotations: @Component, @Repository 
  and @Service, the first being more general and the two last ones having a more specific 
  Semantic for the various particular cases. In this case, for example, using the @Service 
  annotation was more appropriate to registering the ProductService for self-explanatory reasons 
 */
@Service
public class ProductService {

	/*In order for Spring Framework to provide the required dependence definitions and associate an 
	instance of ProductRepository at ProductService, it is required to put the following annotation 
	above the Dependency attribute*/
	@Autowired
	private ProductRepository productRepository;
	
	//Finding All Products/Database Table Rows
	
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	//Finding Products/Database Table Rows by IDs/Primary Keys
	public Product findById(Long id) {
		
		//OBS: the operation findByid() from JPA CRUD returns an Optional-typed object
		Optional<Product> prod = productRepository.findById(id);
		
		//Returning Product type through Optional Object Method .get()
		return prod.get();
		
	}
}

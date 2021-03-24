package com.educandoweb.springBootStudies.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.springBootStudies.entities.Category;
import com.educandoweb.springBootStudies.repositories.CategoryRepository;

/*Registering the class as a Spring Component in order to make it available for the Spring's 
  Dependency-Injection Mechanism which, in this case, will be useful at declaring Category 
  Resources' Dependency to the now Registered Category Services*/

/*OBS:This Registration can be made through different annotations: @Component, @Repository 
  and @Service, the first being more general and the two last ones having a more specific 
  Semantic for the various particular cases. In this case, for example, using the @Service 
  annotation was more appropriate to registering the CategoryService for self-explanatory reasons 
 */
@Service
public class CategoryService {

	/*In order for Spring Framework to provide the required dependence definitions and associate an 
	instance of CategoryRepository at CategoryService, it is required to put the following annotation 
	above the Dependency attribute*/
	@Autowired
	private CategoryRepository categoryRepository;
	
	//Finding All Categories/Database Table Rows
	
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}
	
	//Finding Categories/Database Table Rows by IDs/Primary Keys
	public Category findById(Long id) {
		
		//OBS: the operation findByid() from JPA CRUD returns an Optional-typed object
		Optional<Category> cat = categoryRepository.findById(id);
		
		//Returning Category type through Optional Object Method .get()
		return cat.get();
		
	}
}

package com.educandoweb.springBootStudies.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.springBootStudies.entities.User;
import com.educandoweb.springBootStudies.repositories.UserRepository;
import com.educandoweb.springBootStudies.services.exceptions.DataBaseException;
import com.educandoweb.springBootStudies.services.exceptions.ResourceNotFoundException;

/*Registering the class as a Spring Component in order to make it available for the Spring's 
  Dependency-Injection Mechanism which, in this case, will be useful at declaring User 
  Resources' Dependency to the now Registered User Services*/

/*OBS:This Registration can be made through different annotations: @Component, @Repository 
  and @Service, the first being more general and the two last ones having a more specific 
  Semantic for the various particular cases. In this case, for example, using the @Service 
  annotation was more appropriate to registering the UserService for self-explanatory reasons 
 */
@Service
public class UserService {

	/*In order for Spring Framework to provide the required dependence definitions and associate an 
	instance of UserRepository at UserService, it is required to put the following annotation 
	above the Dependency attribute*/
	@Autowired
	private UserRepository userRepository;
	
	//Finding All Users/Database Table Rows
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	//Finding Users/Database Table Rows by IDs/Primary Keys
	public User findById(Long id) {
		
		//OBS: the operation findByid() from JPA CRUD returns an Optional-typed object
		Optional<User> user = userRepository.findById(id);
		
		//Returning User type through Optional Object Method .get() (with .orElseThrow() Method, if the .get() does not work, a Customized Exception will be thrown
		return user.orElseThrow(() -> new ResourceNotFoundException(id));
		
	}
	//Inserting User at the Database and returning the Inserted User
	public User insertUser(User user) {
		return userRepository.save(user);
	}
	//Deleting the User at the Database
	public void delete(Long id) {
		try {
			
			userRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch (DataIntegrityViolationException e) {

			throw new DataBaseException(e.getMessage());
			
		}
	}
	
	//Updating JPA-Monitored User and then adding it to the Database
	
	public User update (Long id, User editedUser) {
		
		/*Setting and Preparing an User Object "entity" as an entity Monitored by JPA through .getOne() 
		  method (Making its instantiation without Database Interaction)
		 */

		/*OBS: This is a way more effective method in this case, since, differently from getUserById, 
		 the .getOne() method will just prepare the JPA Monitored entity  "entity" instead of pulling 
		 it from the database. In other words, this approach saves a lot of processing */
		
		User entity = userRepository.getOne(id);
		
		//Modifying Monitored User entity before saving it at the Database
		updateData(entity, editedUser);
		
		//Saving updates at the Database
		return userRepository.save(entity);
	}
	//Setting User entity's allowed attributes according to the editedUser set
	private void updateData(User entity, User editedUser) {
		//OBS: Not all user attributes will be allowed to be modified. Id and Password will remain the same 
		entity.setName(editedUser.getName());
		entity.setEmail(editedUser.getEmail());
		entity.setPhone(editedUser.getPhone());
	}

}

package com.educandoweb.springBootStudies.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.springBootStudies.entities.Category;
import com.educandoweb.springBootStudies.entities.Order;
import com.educandoweb.springBootStudies.entities.User;
import com.educandoweb.springBootStudies.entities.enums.OrderStatus;
import com.educandoweb.springBootStudies.repositories.CategoryRepository;
import com.educandoweb.springBootStudies.repositories.OrderRepository;
import com.educandoweb.springBootStudies.repositories.UserRepository;

/*Creating auxiliary class TestConfig that is out of Back End Logical Layers (Resource/Service/Repositories), 
  and will perform some configurations at the application. In this case, it will perform the 
  so-called "Database Seeding". In other words, it will populate the Database with some chosen Objects*/

//Indicating to Spring that this is a specific configuration class with the following annotation
@Configuration
/*Indicating to Spring that this configuration class will be specific for the test profile with 
  the following annotation. To this, one must inform the same spring profile defined
  at application.properties file between its parenthesis */
@Profile("test")

/*In order to EXECUTE Object Instantiation and Saving at the Database (Database Seeding), 
  the TestConfig can implement CommandLineRunner Interface*/
public class TestConfig implements CommandLineRunner{
	
	/*OBS: The Configuration Class must have a weak-coupling dependency with UserRepository/OrderRepository in order to 
	  use its features to access the Database and perform the Database Seeding*/
   
	/*OBS2: Instead of requiring the usual manual Dependency-Injection (through constructors, for example),
     the Spring Framework automatically enables this process through its implicit Dependency-Injection
     Mechanism */
	
	
	/*OBS3: In order for Spring to provide the required dependence definitions and associate an 
	instance of UserRepository/OrderRepository/CategoryRepository at TestConfig, it is required to
    put the following annotation above the Dependency attribute*/
	
	//Declaring UserRepository Dependence
	
	@Autowired
	private UserRepository userRepository;

	//Declaring OrderRepository Dependence
	
	@Autowired
	private OrderRepository orderRepository;

	//Declaring CategoryRepository Dependence
	
	@Autowired
	private CategoryRepository categoryRepository;

	
	/*Implementing CommandLineRunner's override method "run()", which executes all of his body content 
	  once the application is initiated
    */
	@Override
	public void run(String... args) throws Exception {
		
		//Instancing User objects with null IDs once they will be auto-generated as keys at the database
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		//Instancing Order objects with null IDs and related user objects at their constructors to build the DB relation
		//OBS: At H2 Test Database, the local hour will be displayed, which can deviate from the Instant set below
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"),OrderStatus.PAID ,u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"),OrderStatus.DELIVERED, u1); 
		
		//Instancing Category objects with null IDs once they will be auto-generated as keys at the database
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
	
		/*Using Repository objects to perform their respective Repository roles of Data-Accessing while saving the 
		  instanced objects at the Database through Arrays.asList() direct List Instantiation*/
		userRepository.saveAll(Arrays.asList(u1, u2));
	
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
	}

}

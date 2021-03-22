package com.educandoweb.springBootStudies.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.springBootStudies.entities.User;
import com.educandoweb.springBootStudies.repositories.UserRepository;

/*Creating auxiliary class TestConfig that is out of Back End Logical Layers (Resource/Service/DAO), 
  and will perform some configurations at the application. In this case, it will perform the 
  so-called "Database Seeding". In other words, it will populate the Database with some chosen Objects*/

//Indicating to Spring that this is a specific configuration class with the following annotation
@Configuration
/*Indicating to Spring that this configuration class will be specific for the test profile with 
  the following annotation, once informing between its parenthesis the same spring profile defined
  at application.properties file*/
@Profile("test")

/*In order to EXECUTE Object Instantiation and Saving at the Database (Database Seeding), 
  the TestConfig can implement CommandLineRunner Interface*/
public class TestConfig implements CommandLineRunner{
	
	/*OBS: The Configuration Class must have a weak-coupling dependency with UserRepository in order to 
	  use its features to access the Database and perform the Database Seeding*/
   
	/*OBS2: Instead of requiring the usual manual Dependency-Injection (through constructors, for example),
     the Spring Framework automatically enables this process through its implicit Dependency-Injection
     Mechanism  */
	
	//Declaring UserRepository Dependence
	
	/*OBS3: In order for Spring to provide the required dependence definitions and associate an 
	instance of UserRepository at TestConfig, it is required to put the following annotation 
	above the Dependency attribute*/
	@Autowired
	private UserRepository userRepository;

	/*Implementing CommandLineRunner's override method "run()", which executes all of his body content 
	  once the application is initiated
    */
	@Override
	public void run(String... args) throws Exception {
		
		//Instancing user objects with null IDs once they will be auto-generated as keys at the database
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
	
		/*Using UserRepository object to perform the Repository role of Data-Accessing and save the 
		  instanced objects at the Database through Arrays.asList() direct List Instantiation*/
		userRepository.saveAll(Arrays.asList(u1, u2));
		
	}

}
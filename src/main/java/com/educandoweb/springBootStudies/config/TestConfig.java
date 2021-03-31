package com.educandoweb.springBootStudies.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.springBootStudies.Payment;
import com.educandoweb.springBootStudies.entities.Category;
import com.educandoweb.springBootStudies.entities.Order;
import com.educandoweb.springBootStudies.entities.OrderItem;
import com.educandoweb.springBootStudies.entities.Product;
import com.educandoweb.springBootStudies.entities.User;
import com.educandoweb.springBootStudies.entities.enums.OrderStatus;
import com.educandoweb.springBootStudies.repositories.CategoryRepository;
import com.educandoweb.springBootStudies.repositories.OrderItemRepository;
import com.educandoweb.springBootStudies.repositories.OrderRepository;
import com.educandoweb.springBootStudies.repositories.ProductRepository;
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
	
	/*OBS: The Configuration Class must have a weak-coupling dependency with 
	  UserRepository/OrderRepository/CategoryRepository/productRepository in order to 
	  use its features to access the Database and perform the Database Seeding*/
   
	/*OBS2: Instead of requiring the usual manual Dependency-Injection (through constructors, for example),
     the Spring Framework automatically enables this process through its implicit Dependency-Injection
     Mechanism */
	
	
	/*OBS3: In order for Spring to provide the required dependence definitions and associate an 
	instance of UserRepository/OrderRepository/CategoryRepository/productRepository at TestConfig, 
	it is required to put the following annotation above the Dependency attribute*/
	
	//Declaring UserRepository Dependence
	
	@Autowired
	private UserRepository userRepository;

	//Declaring OrderRepository Dependence
	
	@Autowired
	private OrderRepository orderRepository;

	//Declaring CategoryRepository Dependence
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	//Declaring ProductRepository Dependence
	
	@Autowired
	private ProductRepository productRepository;
	
	//Declaring OrderItem Repository Dependence
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	
	/*Implementing CommandLineRunner's override method "run()", which executes all of its body content 
	  once the application is initiated
    */
	@Override
	public void run(String... args) throws Exception {
		
		//Instancing User objects with null IDs once they will be auto-generated as keys at the database
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		//Instancing Order objects with null IDs and related user objects at their constructors to build the DB relation
		//OBS: At H2 Test Database, the local hour will be displayed, which can deviate from the Instant set below
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"),OrderStatus.PAID ,u1);//This PAID Order Object will later be related to a Payment Entity
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"),OrderStatus.DELIVERED, u1); 
		
		/*Using Repository objects to perform their respective Repository roles of Data-Accessing while saving the 
		  instanced objects at the Database (DataBase Seeding)  through Arrays.asList() direct List Instantiation*/
		userRepository.saveAll(Arrays.asList(u1, u2));
	
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
		
		//Instancing Category objects with null IDs once they will be auto-generated as keys at the database
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		//Instancing Product objects with null IDs once they will be auto-generated as keys at the database

		Product prod1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product prod2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product prod3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product prod4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product prod5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, ""); 

	
		/*Using Repository objects to perform their respective Repository roles of Data-Accessing while saving the 
		  instanced objects at the Database (DataBase Seeding)  through Arrays.asList() direct List Instantiation*/
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		productRepository.saveAll(Arrays.asList(prod1,prod2,prod3,prod4,prod5));
		
		/*Once both Product and Category Databases are Seeded by the .saveAll Methods with the respective
		  instantiated objects, it is time to Set the Category/Product Association in order to 
		  define which Category is Related to which Product or vice-versa. To do this, the respective Product objects'
		  Set Collection attributes will be called through its get methods and then will add its Category objects
		  through .add() method*/
		
		prod1.getCategories().add(cat2);
		prod2.getCategories().add(cat1);
		prod2.getCategories().add(cat3);
		prod3.getCategories().add(cat3);
		prod4.getCategories().add(cat3);
		prod5.getCategories().add(cat2);
		
		/*Now that all additions (Associations) are made, it is required to re-save all products to establish 
		  those associations at the databases involved (tb_product, tb_category and tb_product_category).*/
		
		//OBS:This will also automatically Seed the tb_product_category Association Table Database
		
		productRepository.saveAll(Arrays.asList(prod1,prod2,prod3,prod4,prod5));
		
		//Instancing OrderItem Objects according to each Association defined at the Business Diagram
		OrderItem oi1 = new OrderItem(o1, prod1, 2, prod1.getPrice());
		OrderItem oi2 = new OrderItem(o1, prod3, 1, prod3.getPrice());
		OrderItem oi3 = new OrderItem(o2, prod3, 2, prod3.getPrice());
		OrderItem oi4 = new OrderItem(o3, prod5, 2, prod5.getPrice());
		
		/*Using OrderItem Repository object to perform its role of Data-Accessing while saving the 
		  instanced OrderItem objects at the tb_order_item Table (DataBase Seeding) 
		  through Arrays.asList() direct List Instantiation*/
		
		orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3,oi4));
		
		//Setting Order-Dependent Entity Payment object
		Payment payment1 = new Payment(null,Instant.parse("2019-06-20T21:53:07Z"),o1);
		/*OBS: To save a Dependent Object in a One-To-One Relation, no Repositories are needed 
		  for this Object. Instead, the Order Object used to Build the dependent Object (in this case)
		  will set its Payment Attribute with the new Payment Object and then will be added
		  again to the Database through its own Repository (OrderRepository). This way, the JPA
		  will identify and set the One-to-One Association between those two Entities
		 */
		o1.setPayment(payment1);
		orderRepository.save(o1);
		
		
		
		
	}

}

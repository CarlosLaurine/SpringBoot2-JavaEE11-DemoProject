package com.educandoweb.springBootStudies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.springBootStudies.entities.Product;

/*Creating a Product Repository (interface) extending Spring Data JPA's JPA Repository 
  based on the Entity Type and the chosen Id*/

/*OBS: Since Product Repository is already extending a Spring-Registered Interface JPARepository,
  it is exempt of the Registration at the Spring Dependence-Injection Mechanism. Thus, it is not
  required to use annotations such as @Component or @Repository (it is merely optional) 
*/

public interface ProductRepository extends JpaRepository<Product, Long> {

}

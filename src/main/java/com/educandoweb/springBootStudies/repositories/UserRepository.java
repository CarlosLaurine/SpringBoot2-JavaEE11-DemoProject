package com.educandoweb.springBootStudies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.springBootStudies.entities.User;

/*Creating a User Repository (interface) extending Spring Data JPA's JPA Repository 
  based on the Entity Type and the chosen Id*/

/*OBS: In this case it is not required to create the interface implementation,
  since Spring Data JPA has a default implementation for this type of interface.
  Once a Generic JpaRepository with the Entity and the Entity Id is defined
  as being extended, a pre-existent default implementation will automatically occur */



public interface UserRepository extends JpaRepository<User, Long> {

}

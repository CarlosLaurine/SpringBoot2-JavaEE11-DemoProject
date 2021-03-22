package com.educandoweb.springBootStudies.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*Implementing Serializable interface in order to
allow Department Objects to be transformed into
byte sequences, thus ensuring a broader perspective
of manipulation (net roaming, file imprinting, etc)
*/

//Defining it as a DataBase Table
@Entity
/*Signaling to JPA a new name for the auto-generated
  Order table at the database, in order to avoid conflicts
  with the reserved word "ORDER" from SQL*/
@Table(name = "tb_order")
public class Order implements Serializable{
	//Serializable Series Number
	private static final long serialVersionUID = 1L;
	
	//Defining its Primary Key attribute (id) as Auto-Generated by the DataBase
	@Id
	//Since this is a Numeric Key, it will be auto-incremented at the DataBase
	//To state this, the Mapping Command is the following, with the Auto-Incrementation Strategy defined between parenthesis
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	//Using Instant to declare the moment, instead of Date
	//OBS: From Java 8 release on, Date became obsolete to represent moments like in this case
	private Instant moment;
	
	//Defining User association
	/*Implementing the relation between Order and User (Many-to-One)
	  and using the following annotation to indicate to JPA the relation
	  it needs to establish at the DataBase*/
	@ManyToOne
	//Using a second annotation to generate a Foreign Key as a new column at the Order table at the Database, while assigning a nem for this column, which is declared between the following parenthesis
	@JoinColumn(name = "client_id")
	private User client;

	//Since a framework is being used, it is obligatory to set an empty constructor

	public Order() {
		
	}

	public Order(Long id, Instant moment, User client) {
		super();
		this.id = id;
		this.moment = moment;
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	
	//Setting HashCode and Equals based only on Order ID
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
package com.educandoweb.springBootStudies.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.educandoweb.springBootStudies.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

//Defining it as a DataBase Table
@Entity

/*Signaling to JPA a new name for the auto-generated
Order table at the database, in order to avoid conflicts
with SQL's reserved words*/
@Table(name = "tb_order_item")

/*Implementing Serializable interface in order to
allow OrderItem Objects to be transformed into
byte sequences, thus ensuring a broader perspective
of manipulation (net roaming, file imprinting, etc)
*/
public class OrderItem implements Serializable{
	//Serializable Serial Number
	private static final long serialVersionUID = 1L;

	//Setting Identifier Attribute - Correspondent to OrderItem's PrimaryKey (Composite)
	//Using following annotation to signal to JPA that this is a Composite ID
	//OBS: The object must be instanced, since it is an Auxiliary Class-Type
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();
	
	private Integer quantity;
	private Double price;
	
	//Since a framework is being used, it is obligatory to set an empty constructor	
	
	public OrderItem() {
		
	}
	
	//Auto-generating Constructor using Fields (formerly excluding the Id, which will be set manually later)
	//OBS: Both a Product and Order Parameters will be used at the constructor to set the Id attribute (Composite PK) at its Body
	public OrderItem ( Order order, Product product, Integer quantity, Double price) {
		super();
		//Setting the id throughout Product and Order Objects
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}
	
	//Auto-generating Getters and Setters (formerly excluding the Id ones, which will be set manually later)
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	/*Since the OrderItem must provide access to the Product/Order
	pair to which it is related instead of to its Id (Composite PK), 
	it is needed to set Getters and Setters for both Product and Order
    Objects inside this Class */
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}
	
	/*Jackson Library (responsible for JSON Serialization) would produce an
	  undesirable loop display if the following annotation wasn't stated. This happens because 
	  in this case, there would be a double-handed relation between User and Order. 
	  To fix this, it is enough to put the following annotation on one of the relation
	  sides. Preferably at the one that has the oneToMany relation with its pair since 
	  this way it will be possible for the JPA to load all the sides and dependencies
	  without running the risk of crashing the memory (since no Lazy Load will happen due 
	  to Jackson's direct solicitation to the JPA)
	  */
	
	/*OBS: Since Order is NOT a direct Attribute of OrderItem, but instead is accessed through 
	  OrderItem's Id, then the @JsonIgnore Annotation will be placed at its Get Method,
	  that is the significant part in this case for the Java Enterprise Platform since it
	  is through this initial point of the chain reaction that Json calls the associated Order
	  and then starts the chain loop of calls (Order-OrderItem-Order-OrderItem, ad infinitum)*/
	
	@JsonIgnore
	
	public Order getOrder() {
		return id.getOrder();
	}

	public void setOrder(Order order) {
		id.setOrder(order);
	}

	
	//Setting HashCode and Equals based only on OrderItem Id (OrderItemPk's Composite Primary Key)
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
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}

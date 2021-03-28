package com.educandoweb.springBootStudies.entities.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.educandoweb.springBootStudies.entities.Order;
import com.educandoweb.springBootStudies.entities.Product;

//Auxiliary Class with Composite Primary Key

//Using follow annotation to indicate to JPA that this is an Auxiliary Class with Composite PK
@Embeddable
public class OrderItemPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//This Auxiliary Class will NOT have any constructors
	
	//Setting OrderItemPk's References to Order and Product Tables
	
	//Setting OrderItemPK-to-Order Relation
	@ManyToOne
	
	//Using @JoinColumn annotation to generate an Order Foreign Key as a new column at the OrderItemPK table at the Database, while assigning a name for this column, which is declared between the following parenthesis
	@JoinColumn(name = "order_id")	
	private Order order;

	//Setting OrderItemPK-to-Product Relation	
	@ManyToOne
	
	//Using @JoinColumn annotation to generate a Product Foreign Key as a new column at the OrderItemPK table at the Database, while assigning a name for this column, which is declared between the following parenthesis
	@JoinColumn(name = "product_id")
	private Product product;
	
	//Getters and Setters
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	//Generating hashCode and equals based on both Order and Product, since it is the pair that defines the OrderItem
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		OrderItemPK other = (OrderItemPK) obj;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
	
	

	
}

package com.educandoweb.springBootStudies;

import java.io.Serializable;
import java.time.Instant;

import com.educandoweb.springBootStudies.entities.Order;

public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public long id;
	private Instant instant;
	
	private Order order;
	
	public Payment () {
		
	}

	public Payment(long id, Instant instant, Order order) {
		super();
		this.id = id;
		this.instant = instant;
		this.order = order;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Instant getInstant() {
		return instant;
	}

	public void setInstant(Instant instant) {
		this.instant = instant;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Payment other = (Payment) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}

package com.educandoweb.springBootStudies.services.exceptions;

//Custom Exception for possible Invalid Resource Id Searches

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object id) {
		super("Resource not Found! Id = " + id);
	}
}

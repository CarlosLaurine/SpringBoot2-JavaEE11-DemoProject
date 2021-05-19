package com.educandoweb.springBootStudies.services.exceptions;

/*This Custom Exception is responsible to be thrown at Service Layer whenever 
  DataBase Violations happen (usually related to Data Deletion Operations)*/

public class DataBaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataBaseException (String message) {
		
		super(message);
		
	}
}

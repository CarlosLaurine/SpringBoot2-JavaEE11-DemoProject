package com.educandoweb.springBootStudies.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//This Class provides Custom Handling for possible Exceptions triggered

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.springBootStudies.services.exceptions.DataBaseException;
import com.educandoweb.springBootStudies.services.exceptions.ResourceNotFoundException;

//Setting following Annotation in order to intercept the triggered Exceptions and handle them with its respective customized logic
@ControllerAdvice
public class ResourceExceptionHandler {

	//Using following Annotation to define which Type of Exception will be intercepted by this Method
	@ExceptionHandler (DataBaseException.class)
	
	public ResponseEntity<StandardError> dataBaseViolation(DataBaseException e, HttpServletRequest request){
	
		String error = "Database Violation - Error";
		HttpStatus status = HttpStatus.BAD_REQUEST; //Setting HTTP Error Status to 400 - Bad Request
		
		//Fulfilling Standard Error Object with the Custom Information
	
		StandardError stdError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(stdError);
	}
	
	
}

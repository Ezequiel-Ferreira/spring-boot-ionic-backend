package com.example.cursomc.resources.exception;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.NotReadablePropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cursomc.service.exception.DataIntegrityException;
import com.example.cursomc.service.exception.ObjectNotFoundException;

@RestControllerAdvice
public class ResourcesExeptionHandler  {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
	//	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotReadablePropertyException.class)
	public ResponseEntity<StandardError> notReadablePropertyException(NotReadablePropertyException e, HttpServletRequest request){
		
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		
		
			err.addMessage(e.getMessage(), e.getPropertyName());
		
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	
 
}

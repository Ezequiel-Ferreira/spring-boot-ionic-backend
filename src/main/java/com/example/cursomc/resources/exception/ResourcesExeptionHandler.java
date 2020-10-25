package com.example.cursomc.resources.exception;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cursomc.service.exception.ObjectNotFoundException;

@RestControllerAdvice
public class ResourcesExeptionHandler  {
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
	//	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}

}

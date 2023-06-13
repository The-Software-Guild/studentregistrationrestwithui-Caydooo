package com.softra.studentreg.exception;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest req) {
		
		ExceptionResponse er = new ExceptionResponse(new Date(), ex.getMessage(), "detailed description of exception");
		return new ResponseEntity<Object>(er, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({StudentNotFoundException.class})
	public final ResponseEntity<Object> handleUserNotFoundException(StudentNotFoundException ex, WebRequest req) {
		ExceptionResponse er = new ExceptionResponse(new Date(), ex.getMessage(), "Student with specified ID is not in the database!");
		return new ResponseEntity<Object>(er, HttpStatus.NOT_FOUND);
	
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse expRes = new ExceptionResponse(new Date(),"Validation Failed", ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList()));
		
		return new ResponseEntity<Object>(expRes, HttpStatus.BAD_REQUEST);
	}
	
	
	
}


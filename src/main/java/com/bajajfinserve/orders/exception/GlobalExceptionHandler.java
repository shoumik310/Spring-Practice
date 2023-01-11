package com.bajajfinserve.orders.exception;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error handleInvalidOrderId(IllegalArgumentException illegalArgumentException) {
		return new Error (100, illegalArgumentException.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Set<Error> handleInvalidRequest(MethodArgumentNotValidException exception){
		System.out.println("Invalid data passed");
		return exception
					.getAllErrors()
					.stream()
					.map(ObjectError::getDefaultMessage)
					.map(message -> new Error(100, message))
					.collect(Collectors.toSet());
	}
}

class Error {
	private final int code;
	private final String message;
	
	public Error(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}

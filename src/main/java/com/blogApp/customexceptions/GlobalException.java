package com.blogApp.customexceptions;


import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.EXPECTATION_FAILED);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException ex, BindingResult result) {
		HashMap<String, String> map = new HashMap<>();
		List<FieldError> errors = result.getFieldErrors();

		for (FieldError error : errors) {
			map.put(error.getField(), error.getDefaultMessage());
		   
		}
		return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiResponse> accessDeniedException(AccessDeniedException ex) {
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse("you dont have a access to perform this operation",false);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.EXPECTATION_FAILED);
	}
	
}

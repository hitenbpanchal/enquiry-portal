package com.organizemanagemodule.exceptions;

import com.organizemanagemodule.paylods.AuthenticateUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.organizemanagemodule.paylods.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponse> UserNotFoundExceptionHandler(UserNotFoundException ex){
		String message = ex.getMessage();
		ApiResponse response = new ApiResponse(message, false, 404);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		
	}

	@ExceptionHandler(UnAuthenticatedUser.class)
	public AuthenticateUserResponse UnAuthenticatedUserExceptionHandler(UnAuthenticatedUser ex){
		String message = ex.getMessage();
		AuthenticateUserResponse response = new AuthenticateUserResponse();
		response.setMessage(message);
		response.setSuccess(false);
		return response;
	}
}

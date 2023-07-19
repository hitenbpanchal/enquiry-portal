package com.api.gateway.exceptions;

import com.api.gateway.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> response = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String Message = error.getDefaultMessage();
            response.put(fieldName, Message);
        });

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Map<String,String>> unAuthorizedUserHandlerException(HttpClientErrorException ex){
        Map<String,String> response = new HashMap<String,String>();
        String message = "User is unauthenticated";
        response.put("UnauthorizedRequest",message);
        return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserPresentException.class)
    public ResponseEntity<ApiResponse> userPresentExceptionHandler(UserPresentException ex){
        String message = ex.getMessage();
        ApiResponse response =new ApiResponse(message,false,403);
        return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(EmailMustSameAsRegister.class)
    public ResponseEntity<ApiResponse> emailMustSameAsRegisterExceptionHandler(EmailMustSameAsRegister ex){
        String message = ex.getMessage();
        ApiResponse response =new ApiResponse(message,false,403);
        return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(UserNotAuthenticatedException.class)
    public ResponseEntity<ApiResponse> UnAuthorizedUserExceptionHandler(UserNotAuthenticatedException ex){
        String message = ex.getMessage();
        ApiResponse response = new ApiResponse(message,false,401);
        return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
    }
}

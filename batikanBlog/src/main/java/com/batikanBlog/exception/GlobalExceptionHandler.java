package com.batikanBlog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.batikanBlog.utils.ErrorMessage;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
        Map<String,String> exceptionDetails = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String field = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            exceptionDetails.put(field,message);
        });
        return new ResponseEntity<>(exceptionDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ErrorMessage> userExistsExceptionHandler(UserExistsException ex){
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage()),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage()),HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ErrorMessage> userDoesNotExistExceptionHandler(UserDoesNotExistsException ex){
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage()),HttpStatus.NOT_FOUND);
    }


}

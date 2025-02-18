package com.banks.accounts.Exceptions;

import com.banks.accounts.dtos.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> globalExceptionHandler(Exception ex){
        ResponseDTO response = new ResponseDTO();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(CustomerAlreadyExists.class)
    public ResponseEntity<ResponseDTO> customerAlreadyExists(CustomerAlreadyExists ex){
        ResponseDTO response = new ResponseDTO();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ResponseDTO> resourceNotFound(ResourceNotFound ex) {
        ResponseDTO response = new ResponseDTO();
        response.setStatus(HttpStatus.NOT_FOUND);
        response.setMessage(ex.getMessage()); // Extracting message from the exception
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage()); // Only store default messages
        }
        ResponseDTO response = new ResponseDTO();
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setMessage("Please read the errors in the data");
        response.setData(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}

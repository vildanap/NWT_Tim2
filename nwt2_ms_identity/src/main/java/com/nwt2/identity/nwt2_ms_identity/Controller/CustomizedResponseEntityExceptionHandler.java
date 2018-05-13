package com.nwt2.like.nwt2_ms_like.Controller;

import com.nwt2.identity.nwt2_ms_identity.Controller.CustomErrorType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorString="";
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errorString+=error.getDefaultMessage()+"\n";
        }
        CustomErrorType errorDetails = new CustomErrorType(errorString);
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }
}

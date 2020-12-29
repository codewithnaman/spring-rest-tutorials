package com.codewithnaman.exception.handlers;

import com.codewithnaman.dto.error.FieldValidationErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class FieldValidationExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<FieldValidationErrorResponse> fieldValidationResponse = new ArrayList<>();
        Map<String, String> fieldErrors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                fieldErrors.computeIfAbsent(fieldError.getField(), key -> fieldError.getDefaultMessage())
        );
        fieldErrors.forEach((key, value) -> fieldValidationResponse.add(new FieldValidationErrorResponse(key,value)));
        return new ResponseEntity(fieldValidationResponse,HttpStatus.BAD_REQUEST);
    }
}

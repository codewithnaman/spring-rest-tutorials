package com.codewithnaman.exception.handlers;

import com.codewithnaman.dto.error.BusinessExceptionErrorResponse;
import com.codewithnaman.dto.todo.UpdateTaskStatusException;
import com.codewithnaman.exception.BusinessException;
import com.codewithnaman.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BusinessExceptionErrorResponse> handleBusinessException(BusinessException exception) {
        if (exception instanceof EntityNotFoundException) {
            ResponseEntity<BusinessExceptionErrorResponse> responseEntity = new ResponseEntity<>(
                    new BusinessExceptionErrorResponse(exception.getErrorCode(), exception.getMessage()),
                    HttpStatus.NOT_FOUND);
            return responseEntity;
        }else if(exception instanceof UpdateTaskStatusException){
            ResponseEntity<BusinessExceptionErrorResponse> responseEntity = new ResponseEntity<>(
                    new BusinessExceptionErrorResponse(exception.getErrorCode(), exception.getMessage()),
                    HttpStatus.UNPROCESSABLE_ENTITY);
            return responseEntity;
        }
        return new ResponseEntity<>(
                new BusinessExceptionErrorResponse(exception.getErrorCode(), exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}

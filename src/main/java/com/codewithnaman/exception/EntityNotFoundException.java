package com.codewithnaman.exception;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(String message){
        super("RESOURCE_NOT_FOUND",message);
    }
}

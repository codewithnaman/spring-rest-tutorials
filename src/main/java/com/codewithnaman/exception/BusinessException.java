package com.codewithnaman.exception;

import lombok.Getter;

public class BusinessException extends Exception {

    @Getter
    private String errorCode;

    public BusinessException(String errorCode, String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
    }
}

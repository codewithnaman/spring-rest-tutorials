package com.codewithnaman.dto.error;

import lombok.Data;

@Data
public class BusinessExceptionErrorResponse {

    private String errorCode;

    private String errorMessage;

    public BusinessExceptionErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.errorMessage = message;
    }
}

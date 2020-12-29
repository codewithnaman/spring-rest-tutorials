package com.codewithnaman.dto.todo;

import com.codewithnaman.exception.BusinessException;

public class UpdateTaskStatusException extends BusinessException {

    public UpdateTaskStatusException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}

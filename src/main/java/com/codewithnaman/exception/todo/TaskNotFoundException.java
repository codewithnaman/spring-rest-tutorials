package com.codewithnaman.exception.todo;

import com.codewithnaman.exception.EntityNotFoundException;

public class TaskNotFoundException extends EntityNotFoundException {

    public TaskNotFoundException(String message) {
        super(message);
    }
}

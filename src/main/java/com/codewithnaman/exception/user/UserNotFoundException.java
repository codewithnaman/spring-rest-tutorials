package com.codewithnaman.exception.user;

import com.codewithnaman.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(String userNameOrID){
        super("User Not found with provided input : "+userNameOrID);
    }
}

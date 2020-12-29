package com.codewithnaman.dto.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CreateUserRequest {

    @NotEmpty
    @Size(min = 5,max = 255)
    private String userName;

    @NotEmpty
    @Size(min = 3,max = 255)
    private String firstName;

    @NotEmpty
    @Size(min = 2,max = 255)
    private String lastName;
}

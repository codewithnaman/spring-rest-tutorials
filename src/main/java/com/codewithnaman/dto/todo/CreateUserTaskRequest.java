package com.codewithnaman.dto.todo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateUserTaskRequest {

    @NotEmpty
    private String taskName;

    private String taskDescription;
}

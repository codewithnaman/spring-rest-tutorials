package com.codewithnaman.dto.todo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "Task Request",description = "Request Body for the Task")
public class CreateUserTaskRequest {

    @NotEmpty
    @ApiModelProperty(value = "Task Name")
    private String taskName;

    @ApiModelProperty(value = "Task Description",allowEmptyValue = true)
    private String taskDescription;
}

package com.codewithnaman.controller.todo;

import com.codewithnaman.dto.todo.CreateUserTaskRequest;
import com.codewithnaman.entity.Todo;
import com.codewithnaman.exception.user.UserNotFoundException;
import com.codewithnaman.service.todo.CreateUserTaskService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
@Api(value = "Create User Tasks")
public class CreateUserTaskController {

    private final CreateUserTaskService createUserTaskService;

    public CreateUserTaskController(CreateUserTaskService createUserTaskService) {
        this.createUserTaskService = createUserTaskService;
    }

    @PostMapping("users/{userId}/tasks")
    @ApiOperation(value = "Create User Task")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "User Task Created"),
                    @ApiResponse(code = 404, message = "User Not found for which task need to be created")
            }
    )
    public ResponseEntity<Object> createUserTask(
            @PathVariable
            @ApiParam(value = "User ID of user for which task to be created", required = true)
                    String userId,
            @RequestBody @Valid
                    CreateUserTaskRequest createUserTaskRequest) throws UserNotFoundException {
        log.info("Create User Task Request Received for User ID : {}", userId);
        Todo todo = createUserTaskService.createNewUserTask(userId, createUserTaskRequest);
        log.info("Task Created Successfully for User ID : {} with task ID : {}", userId, todo.getId());
        URI createdTodoUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{taskId}").buildAndExpand(todo.getId()).toUri();
        return ResponseEntity.created(createdTodoUri).build();
    }
}

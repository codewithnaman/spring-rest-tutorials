package com.codewithnaman.controller.todo;

import com.codewithnaman.dto.todo.CreateUserTaskRequest;
import com.codewithnaman.entity.Todo;
import com.codewithnaman.exception.user.UserNotFoundException;
import com.codewithnaman.service.todo.CreateUserTaskService;
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
public class CreateUserTask {

    private final CreateUserTaskService createUserTaskService;

    public CreateUserTask(CreateUserTaskService createUserTaskService) {
        this.createUserTaskService = createUserTaskService;
    }

    @PostMapping("users/{userId}/tasks")
    public ResponseEntity<Object> createUserTask(@PathVariable String userId,
                                                 @RequestBody @Valid CreateUserTaskRequest createUserTaskRequest) throws UserNotFoundException {
        log.info("Create User Task Request Received for User ID : {}", userId);
        Todo todo = createUserTaskService.createNewUserTask(userId, createUserTaskRequest);
        log.info("Task Created Successfully for User ID : {} with task ID : {}", userId, todo.getId());
        URI createdTodoUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{taskId}").buildAndExpand(todo.getId()).toUri();
        return ResponseEntity.created(createdTodoUri).build();
    }
}

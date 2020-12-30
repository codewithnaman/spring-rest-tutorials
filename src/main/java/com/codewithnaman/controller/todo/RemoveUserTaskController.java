package com.codewithnaman.controller.todo;

import com.codewithnaman.exception.EntityNotFoundException;
import com.codewithnaman.service.todo.DeleteUserTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RemoveUserTaskController {

    private final DeleteUserTaskService deleteUserTaskService;

    public RemoveUserTaskController(DeleteUserTaskService deleteUserTaskService) {
        this.deleteUserTaskService = deleteUserTaskService;
    }

    @DeleteMapping("users/{userId}/tasks/{taskId}")
    public ResponseEntity<Object> removeUserTask(@PathVariable String userId, @PathVariable Long taskId) throws EntityNotFoundException {
        log.info("Remove User Task Request Received for User ID : {} and Task ID : {}", userId, taskId);
        deleteUserTaskService.deleteTask(userId, taskId);
        log.info("Remove User Task Request Completed Successfully for User ID : {} and Task ID : {}", userId, taskId);
        return ResponseEntity.accepted().build();
    }
}

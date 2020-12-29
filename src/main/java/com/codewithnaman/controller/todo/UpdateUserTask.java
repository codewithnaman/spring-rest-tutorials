package com.codewithnaman.controller.todo;

import com.codewithnaman.constants.TaskStatus;
import com.codewithnaman.dto.todo.UpdateTaskStatusException;
import com.codewithnaman.entity.Todo;
import com.codewithnaman.exception.BusinessException;
import com.codewithnaman.exception.EntityNotFoundException;
import com.codewithnaman.service.todo.UpdateUserTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UpdateUserTask {

    private final UpdateUserTaskService updateUserTaskService;

    public UpdateUserTask(UpdateUserTaskService updateUserTaskService) {
        this.updateUserTaskService = updateUserTaskService;
    }

    @PutMapping("users/{userId}/tasks/{taskId}/{status}")
    public Todo updateUserTaskStatus(@PathVariable String userId,
                                     @PathVariable Long taskId,
                                     @PathVariable TaskStatus status) throws BusinessException {
        log.info("User Task Update Request Received. User ID : {}, Task ID : {} and Status: {}",userId,taskId,status);
        if (status == TaskStatus.NEW) {
            log.error("Update Denied as User ID : {} Task ID : {} is trying to put the task in NEW Status", userId, taskId);
            throw new UpdateTaskStatusException("INVALID_TASK_STATUS_PROVIDED", "Update task to new denied");
        }
        Todo todo = updateUserTaskService.updateUserTask(userId, taskId, status);
        log.info("User Task Updated. User ID : {}, Task ID : {} and Status: {}",userId,taskId,status);
        return todo;
    }
}

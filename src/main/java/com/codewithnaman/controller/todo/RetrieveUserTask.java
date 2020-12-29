package com.codewithnaman.controller.todo;

import com.codewithnaman.entity.Todo;
import com.codewithnaman.exception.EntityNotFoundException;
import com.codewithnaman.exception.user.UserNotFoundException;
import com.codewithnaman.service.todo.RetrieveUserTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class RetrieveUserTask {

    private final RetrieveUserTaskService retrieveUserTaskService;

    public RetrieveUserTask(RetrieveUserTaskService retrieveUserTaskService) {
        this.retrieveUserTaskService = retrieveUserTaskService;
    }

    @GetMapping("users/{userId}/tasks/")
    public List<Todo> findAllUserTasks(@PathVariable String userId) throws UserNotFoundException {
        log.info("Request Received for All tasks of User ID: {}",userId);
        List<Todo> tasks = retrieveUserTaskService.findAllUserTask(userId);
        log.info("No. of Tasks found for User ID {} : {}",userId,tasks.size());
        return tasks;
    }

    @GetMapping("users/{userId}/tasks/{taskId}")
    public Todo findAllUserTasks(@PathVariable String userId, @PathVariable Long taskId) throws EntityNotFoundException {
        log.info("Request Received for User ID : {} and Task ID: {}",userId,taskId);
        Todo todo = retrieveUserTaskService.findUserTaskByTaskID(userId,taskId);
        log.info("Task found for the User ID : {} and Task ID : {}",userId,taskId);
        return todo;
    }
}

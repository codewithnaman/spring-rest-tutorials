package com.codewithnaman.service.todo;

import com.codewithnaman.entity.Todo;
import com.codewithnaman.entity.User;
import com.codewithnaman.exception.EntityNotFoundException;
import com.codewithnaman.exception.todo.TaskNotFoundException;
import com.codewithnaman.exception.user.UserNotFoundException;
import com.codewithnaman.respository.TodoRepository;
import com.codewithnaman.service.user.RetrieveUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RetrieveUserTaskService {

    private final RetrieveUserService retrieveUserService;
    private final TodoRepository todoRepository;

    public RetrieveUserTaskService(RetrieveUserService retrieveUserService, TodoRepository todoRepository) {
        this.retrieveUserService = retrieveUserService;
        this.todoRepository = todoRepository;
    }


    public List<Todo> findAllUserTask(String userId) throws UserNotFoundException {
        log.info("Finding all user tasks for User ID : {}", userId);
        User user = retrieveUserService.getUserById(userId);
        List<Todo> tasks = user.getTasks();
        log.info("Tasks found for UserName : {}, Number of task found for the user is : {}", tasks.size());
        return tasks;
    }

    public Todo findUserTaskByTaskID(String userId, Long taskId) throws EntityNotFoundException {
        log.info("Finding task for User ID : {} and Task Id : {}", userId, taskId);
        User user = retrieveUserService.getUserById(userId);
        Optional<Todo> taskHolder = todoRepository.findByUserAndId(user, taskId);
        if(taskHolder.isPresent()){
            log.info("Task found for the User ID : {} and Task ID : {}",userId,taskId);
            return taskHolder.get();
        }
        log.error("Task not found for User ID : {} and Task ID :{}",userId,taskId);
        throw new TaskNotFoundException("Task not found for the provided input");
    }
}

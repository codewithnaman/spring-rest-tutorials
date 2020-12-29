package com.codewithnaman.service.todo;

import com.codewithnaman.entity.Todo;
import com.codewithnaman.exception.EntityNotFoundException;
import com.codewithnaman.respository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeleteUserTaskService {

    private final RetrieveUserTaskService retrieveUserTaskService;
    private final TodoRepository todoRepository;

    public DeleteUserTaskService(RetrieveUserTaskService retrieveUserTaskService, TodoRepository todoRepository) {
        this.retrieveUserTaskService = retrieveUserTaskService;
        this.todoRepository = todoRepository;
    }

    public void deleteTask(String userId, Long taskId) throws EntityNotFoundException {
        log.info("Deleting task for User ID : {} and Task ID :{}", userId, taskId);
        Todo todo = retrieveUserTaskService.findUserTaskByTaskID(userId, taskId);
        todoRepository.delete(todo);
        log.info("Task Deleted successfully for User ID : {} and Task ID : {}", userId, taskId);
    }
}

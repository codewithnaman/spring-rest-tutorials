package com.codewithnaman.service.todo;

import com.codewithnaman.constants.TaskStatus;
import com.codewithnaman.dto.todo.CreateUserTaskRequest;
import com.codewithnaman.entity.Todo;
import com.codewithnaman.entity.User;
import com.codewithnaman.entity.embeddable.RecordMetadata;
import com.codewithnaman.exception.user.UserNotFoundException;
import com.codewithnaman.respository.TodoRepository;
import com.codewithnaman.service.user.RetrieveUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateUserTaskService {

    private final RetrieveUserService retrieveUserService;
    private final TodoRepository todoRepository;

    public CreateUserTaskService(RetrieveUserService retrieveUserService,TodoRepository todoRepository) {
        this.retrieveUserService = retrieveUserService;
        this.todoRepository = todoRepository;
    }

    public Todo createNewUserTask(String id, CreateUserTaskRequest createUserTaskRequest) throws UserNotFoundException {
        log.info("Creating task for User ID : {} and Task Name : {}",id,createUserTaskRequest.getTaskName());
        User user = retrieveUserService.getUserById(id);
        Todo todo = new Todo();
        todo.setUser(user);
        todo.setTaskName(createUserTaskRequest.getTaskName());
        todo.setTaskDescription(createUserTaskRequest.getTaskDescription());
        todo.setTaskStatus(TaskStatus.NEW);
        todo.setMetadata(RecordMetadata.getRecordMetaDataWithCurrentTime());
        todo = todoRepository.save(todo);
        log.info("Task created Successfully for Username : {} with Task ID : {}",todo.getId());
        return todo;
    }
}

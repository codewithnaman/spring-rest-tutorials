package com.codewithnaman.service.todo;

import com.codewithnaman.constants.TaskStatus;
import com.codewithnaman.entity.Todo;
import com.codewithnaman.entity.embeddable.RecordMetadata;
import com.codewithnaman.exception.EntityNotFoundException;
import com.codewithnaman.respository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class UpdateUserTaskService {

    private final RetrieveUserTaskService retrieveUserTaskService;

    private final TodoRepository todoRepository;

    public UpdateUserTaskService(RetrieveUserTaskService retrieveUserTaskService, TodoRepository todoRepository) {
        this.retrieveUserTaskService = retrieveUserTaskService;
        this.todoRepository = todoRepository;
    }

    public Todo updateUserTask(String userId, Long taskId, TaskStatus status) throws EntityNotFoundException {
        log.info("Updating User Task. User ID : {}, Task ID : {} and Status : {}", userId, taskId, status);
        Todo task = retrieveUserTaskService.findUserTaskByTaskID(userId, taskId);
        switch (task.getTaskStatus()) {
            case NEW:
                task.setStartTime(LocalDateTime.now());
                switch (status) {
                    case IN_PROGRESS:
                        log.info("Task moved from NEW to IN_PROGRESS");
                        task.setTaskStatus(TaskStatus.IN_PROGRESS);
                        break;
                    case COMPLETED:
                        log.info("Task moved from NEW to COMPLETED");
                        task.setTaskStatus(TaskStatus.COMPLETED);
                        task.setCompletionTime(LocalDateTime.now());
                        break;
                }
                break;
            case PENDING:
                switch (status){
                    case IN_PROGRESS:
                        log.info("Task moved from PENDING to IN_PROGRESS");
                        task.setTaskStatus(TaskStatus.IN_PROGRESS);
                        break;
                    case COMPLETED:
                        log.info("Task moved from PENDING to COMPLETED");
                        task.setTaskStatus(TaskStatus.COMPLETED);
                        task.setCompletionTime(LocalDateTime.now());
                        break;
                }
                break;
            case IN_PROGRESS:
                switch (status){
                    case PENDING:
                        log.info("Task moved from IN_PROGRESS to PENDING");
                        task.setTaskStatus(TaskStatus.PENDING);
                        break;
                    case COMPLETED:
                        log.info("Task moved from IN_PROGRESS to COMPLETED");
                        task.setCompletionTime(LocalDateTime.now());
                        task.setTaskStatus(TaskStatus.COMPLETED);
                        break;
                }
            case COMPLETED:
                switch (status){
                    case PENDING:
                        log.info("Task moved from COMPLETED to PENDING");
                        task.setCompletionTime(null);
                        task.setTaskStatus(TaskStatus.PENDING);
                        break;
                    case IN_PROGRESS:
                        log.info("Task moved from COMPLETED to IN_PROGRESS");
                        task.setCompletionTime(null);
                        task.setTaskStatus(TaskStatus.IN_PROGRESS);
                        break;
                }

        }
        task.setMetadata(RecordMetadata.getRecordMetaDataWithUpdateDateSetToCurrentTime());
        task = todoRepository.save(task);
        log.info("Task updated successfully. User ID : {}, Task ID : {} and Status : {}",userId,taskId,status);
        return task;
    }
}

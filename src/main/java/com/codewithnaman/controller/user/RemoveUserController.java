package com.codewithnaman.controller.user;

import com.codewithnaman.exception.user.UserNotFoundException;
import com.codewithnaman.service.user.DeleteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RemoveUserController {

    private final DeleteUserService deleteUserService;

    public RemoveUserController(DeleteUserService deleteUserService) {
        this.deleteUserService = deleteUserService;
    }

    @DeleteMapping("users/{idOrUserName}")
    public ResponseEntity<Object> ResponseEntityremoveUser(@PathVariable String idOrUserName) throws UserNotFoundException {
        log.info("Remove user request received for user ID or Username : {}",idOrUserName);
        deleteUserService.deleteUser(idOrUserName);
        log.info("Removed User with user ID or Username : {}",idOrUserName);
        return ResponseEntity.accepted().build();
    }
}

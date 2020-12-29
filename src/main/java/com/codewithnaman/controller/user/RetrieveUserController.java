package com.codewithnaman.controller.user;

import com.codewithnaman.entity.User;
import com.codewithnaman.exception.user.UserNotFoundException;
import com.codewithnaman.service.user.RetrieveUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class RetrieveUserController {

    private final RetrieveUserService retrieveUserService;

    public RetrieveUserController(RetrieveUserService retrieveUserService) {
        this.retrieveUserService = retrieveUserService;
    }

    @GetMapping("users")
    public List<User> getAllUsers() {
        log.info("Get All user request received");
        List<User> users = retrieveUserService.getAllUsers();
        log.info("Found number of Users : {}", users.size());
        return users;
    }

    @GetMapping("users/{searchBy}/{idOrUserName}")
    public User getAllUsers(@PathVariable String searchBy, @PathVariable String idOrUserName) throws UserNotFoundException {
        User user = null;
        switch (searchBy) {
            case "username":
                log.info("User Retrieval request received for Username : {}", idOrUserName);
                user = retrieveUserService.getUserByUserName(idOrUserName);
                log.info("User found with Username : {}", idOrUserName);
                return user;
            case "id":
                log.info("User Retrieval request received for ID : {}", idOrUserName);
                user = retrieveUserService.getUserById(idOrUserName);
                log.info("User found with ID : {}", idOrUserName);
                return user;
            default:
                log.error("Invalid search criteria : {} provided", searchBy);
                throw new RuntimeException("Invalid search criteria provided");
        }
    }
}

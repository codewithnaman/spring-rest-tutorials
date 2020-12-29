package com.codewithnaman.controller.user;

import com.codewithnaman.entity.User;
import com.codewithnaman.exception.user.UserNotFoundException;
import com.codewithnaman.service.user.RetrieveUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RetrieveUserController {

    private final RetrieveUserService retrieveUserService;

    public RetrieveUserController(RetrieveUserService retrieveUserService) {
        this.retrieveUserService = retrieveUserService;
    }

    @GetMapping("users")
    public List<User> getAllUsers(){
        return retrieveUserService.getAllUsers();
    }

    @GetMapping("users/{searchBy}/{idOrUserName}")
    public User getAllUsers(@PathVariable String searchBy,@PathVariable String idOrUserName) throws UserNotFoundException {
        switch (searchBy){
            case "username":
                return retrieveUserService.getUserByUserName(idOrUserName);
            case "id":
                return retrieveUserService.getUserById(idOrUserName);
            default:
                throw new RuntimeException("Invalid search criteria provided");
        }
    }
}

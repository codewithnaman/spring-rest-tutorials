package com.codewithnaman.controller.user;

import com.codewithnaman.dto.user.CreateUserRequest;
import com.codewithnaman.entity.User;
import com.codewithnaman.service.user.CreateUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
public class CreateUserController {

    private final CreateUserService createUserService;

    public CreateUserController(CreateUserService createUserService) {
        this.createUserService = createUserService;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> registerUserUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        log.info("Register new user request received with Username: {}", createUserRequest.getUserName());
        User registeredUser = this.createUserService.createUser(createUserRequest);
        URI registeredUserUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(registeredUser.getId())
                .toUri();
        log.info("User registration successful with  : {}", registeredUser.getUserName());
        return ResponseEntity.created(registeredUserUri).build();
    }
}

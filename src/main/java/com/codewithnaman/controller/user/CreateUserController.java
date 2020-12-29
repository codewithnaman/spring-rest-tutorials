package com.codewithnaman.controller.user;

import com.codewithnaman.dto.user.CreateUserRequest;
import com.codewithnaman.entity.User;
import com.codewithnaman.service.user.CreateUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class CreateUserController {

    private final CreateUserService createUserService;

    public CreateUserController(CreateUserService createUserService) {
        this.createUserService = createUserService;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> registerUserUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        User registeredUser = this.createUserService.createUser(createUserRequest);
        URI registeredUserUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(registeredUser.getId())
                .toUri();
        return ResponseEntity.created(registeredUserUri).build();
    }
}

package com.codewithnaman.service.user;

import com.codewithnaman.dto.user.CreateUserRequest;
import com.codewithnaman.entity.User;
import com.codewithnaman.entity.embeddable.RecordMetadata;
import com.codewithnaman.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateUserService {

    private final UserRepository userRepository;

    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequest createUserRequest) {
        log.info("Registering new user with user name : {}", createUserRequest.getUserName());
        if (userRepository.findByUserName(createUserRequest.getUserName()).isPresent()) {
            log.error("User Already exist with user name : {}", createUserRequest.getUserName());
            throw new RuntimeException("User is already present");
        }
        User user = new User();
        user.setUserName(createUserRequest.getUserName());
        user.setFirstName(createUserRequest.getFirstName());
        user.setLastName(createUserRequest.getLastName());
        user.setMetadata(RecordMetadata.getRecordMetaDataWithCurrentTime());
        user = userRepository.save(user);
        log.info("User registered successfully. UserName : {} and ID : {}",user.getUserName(),user.getId());
        return user;
    }
}

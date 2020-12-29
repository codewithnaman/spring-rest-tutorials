package com.codewithnaman.service.user;

import com.codewithnaman.dto.user.CreateUserRequest;
import com.codewithnaman.entity.User;
import com.codewithnaman.entity.embeddable.RecordMetadata;
import com.codewithnaman.respository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {

    private final UserRepository userRepository;

    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequest createUserRequest) {
        if(userRepository.findByUserName(createUserRequest.getUserName()).isPresent()){
            throw new RuntimeException("User is already present");
        }
        User user = new User();
        user.setUserName(createUserRequest.getUserName());
        user.setFirstName(createUserRequest.getFirstName());
        user.setLastName(createUserRequest.getLastName());
        user.setMetadata(RecordMetadata.getRecordMetaDataWithCurrentTime());
        return userRepository.save(user);
    }
}

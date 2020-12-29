package com.codewithnaman.service.user;

import com.codewithnaman.entity.User;
import com.codewithnaman.exception.user.UserNotFoundException;
import com.codewithnaman.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RetrieveUserService {

    private final UserRepository userRepository;

    public RetrieveUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        log.info("Finding all users");
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        log.info("Found number of users : {}", users.size());
        return users;
    }

    public User getUserByUserName(String userName) throws UserNotFoundException {
        log.info("Finding user with Username : {}", userName);
        Optional<User> user = userRepository.findByUserName(userName);
        if (user.isPresent()){
            log.info("Found user with Username : {}", userName);
            return user.get();
        }
        log.error("User Not found wih Username : {}");
        throw new UserNotFoundException(userName);
    }

    public User getUserById(String id) throws UserNotFoundException {
        if (NumberUtils.isCreatable(id)) {
            log.info("Finding user with ID : {}", id);
            Optional<User> user = userRepository.findById(Long.valueOf(id));
            if (user.isPresent()) {
                log.info("Found user with ID : {}", id);
                return user.get();
            }
        }
        log.error("User Not found wih ID : {}");
        throw new UserNotFoundException(id);
    }
}

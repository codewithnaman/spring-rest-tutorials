package com.codewithnaman.service.user;

import com.codewithnaman.entity.User;
import com.codewithnaman.exception.user.UserNotFoundException;
import com.codewithnaman.respository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RetrieveUserService {

    private final UserRepository userRepository;

    public RetrieveUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
       List<User> users = new ArrayList<>();
       userRepository.findAll().forEach(users::add);
       return users;
    }

    public User getUserByUserName(String userName) throws UserNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);
        if (user.isPresent()){
            return user.get();
        }
        throw new UserNotFoundException(userName);
    }

    public User getUserById(String id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(Long.valueOf(id));
        if (user.isPresent()){
            return user.get();
        }
        throw new UserNotFoundException(id);
    }
}

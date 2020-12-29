package com.codewithnaman.service.user;

import com.codewithnaman.entity.User;
import com.codewithnaman.exception.user.UserNotFoundException;
import com.codewithnaman.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DeleteUserService {

    private final UserRepository userRepository;

    public DeleteUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void deleteUser(String idOrUserName) throws UserNotFoundException {
        log.info("Delete User Request for ID or Username : {}", idOrUserName);
        Optional<User> userHolder = userRepository.findByUserName(idOrUserName);
        if (!userHolder.isPresent() && NumberUtils.isCreatable(idOrUserName)) {
            userHolder = userRepository.findById(Long.valueOf(idOrUserName));
        }
        if (userHolder.isPresent()) {
            User user = userHolder.get();
            userRepository.delete(user);
            log.info("User Deleted for Username : {}", user.getUserName());
        } else {
            throw new UserNotFoundException(idOrUserName);
        }
    }

}

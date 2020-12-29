package com.codewithnaman.respository;

import com.codewithnaman.entity.Todo;
import com.codewithnaman.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface TodoRepository extends PagingAndSortingRepository<Todo,Long> {

    Optional<Todo> findByUserAndId(User user, Long id);
}

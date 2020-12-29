package com.codewithnaman.respository;

import com.codewithnaman.entity.Todo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TodoRepository extends PagingAndSortingRepository<Todo,Long> {
}

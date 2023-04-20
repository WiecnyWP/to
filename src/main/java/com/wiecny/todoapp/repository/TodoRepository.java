package com.wiecny.todoapp.repository;

import com.wiecny.todoapp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    @Query(value = "SELECT * FROM TODO WHERE TRUNC(EXECUTION_DATE) = CURRENT_DATE", nativeQuery = true)
    List<Todo> getTodayTodos();

}

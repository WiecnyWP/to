package com.wiecny.todoapp.repository.cache;

import com.wiecny.todoapp.model.Todo;

import java.util.List;

public interface TodoCache extends Cache {

    List<Todo> getTodayTodos();

    List<Todo> findAll();

    Todo add(Todo entity);

}

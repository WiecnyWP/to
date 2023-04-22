package com.wiecny.todoapp.service;

import com.wiecny.todoapp.dto.TodoDTO;
import com.wiecny.todoapp.model.MyEntity;
import com.wiecny.todoapp.model.Prototype;
import com.wiecny.todoapp.model.Todo;
import com.wiecny.todoapp.repository.cache.TodoCache;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {

    private TodoCache todoCacheImpl;

    public void save(TodoDTO dto) {
        Todo todo = Todo.builder()
                .description(dto.getDescription())
                .priority(dto.getPriority())
                .executionDate(dto.getExecutionDate())
                .done(false)
                .build();

        todoCacheImpl.add(todo);
    }

    public void markDone(int id) {
        Todo todo = getById(id);
        todo.setDone(true);
        todoCacheImpl.add(todo);
    }

    public TodoDTO deleteById(int id) {
        Todo todo = getById(id);
        todoCacheImpl.removeById(id);
        return convertModelToDTO(todo);
    }

    public List<Todo> getTodayTodos() {
        return todoCacheImpl.getTodayTodos();
    }

    public MyEntity getCloneById(int id) {
        Prototype prototype = getById(id);
        return prototype.clone();
    }

    private TodoDTO convertModelToDTO(Todo todo) {
        return TodoDTO.builder()
                .description(todo.getDescription())
                .build();
    }

    private Todo getById(Integer id) {
        return (Todo) todoCacheImpl.getById(id);
    }
}

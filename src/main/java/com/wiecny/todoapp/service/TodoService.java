package com.wiecny.todoapp.service;

import com.wiecny.todoapp.dto.TodoDTO;
import com.wiecny.todoapp.model.MyEntity;
import com.wiecny.todoapp.model.Prototype;
import com.wiecny.todoapp.model.Todo;
import com.wiecny.todoapp.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoService {

    private TodoRepository todoRepository;

    public void save(TodoDTO dto) {
        Todo todo = Todo.builder()
                .description(dto.getDescription())
                .priority(dto.getPriority())
                .executionDate(dto.getExecutionDate())
                .done(false)
                .build();

        todoRepository.save(todo);
    }

    public void markDone(int id) {
        Todo todo = getById(id);
        todo.setDone(true);
        todoRepository.save(todo);
    }

    public TodoDTO deleteById(int id) {
        Todo todo = getById(id);
        todoRepository.deleteById(id);
        return convertModelToDTO(todo);
    }

    public List<Todo> getTodayTodos() {
        return todoRepository.getTodayTodos();
    }

    public MyEntity getCloneById(int id) {
        Prototype prototype = getById(id);
        MyEntity entity = prototype.clone();
        return entity;
    }


    private TodoDTO convertModelToDTO(Todo todo) {
        return TodoDTO.builder()
                .description(todo.getDescription())
                .build();
    }

    private Todo getById(Integer id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            return optionalTodo.get();
        } else {
            throw new IllegalArgumentException("TODO not found in db");
        }
    }
}

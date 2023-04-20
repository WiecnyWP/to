package com.wiecny.todoapp.service;

import com.wiecny.todoapp.model.Todo;
import com.wiecny.todoapp.model.TodoDTO;
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

    public void deleteById(int id) {
        todoRepository.deleteById(id);
    }

    private Todo getById(Integer id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            return optionalTodo.get();
        } else {
            throw new IllegalArgumentException("TODO not found in db");
        }
    }

    public List<Todo> getTodayTodos() {
        return todoRepository.getTodayTodos();
    }
}

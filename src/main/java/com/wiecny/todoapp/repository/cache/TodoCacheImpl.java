package com.wiecny.todoapp.repository.cache;

import com.wiecny.todoapp.component.ThirtyMinutesTimeObserver;
import com.wiecny.todoapp.model.Todo;
import com.wiecny.todoapp.repository.TodoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TodoCacheImpl implements TodoCache {

    private final TodoRepository todoRepository;

    private List<Todo> cache = new ArrayList<>();

    @PostConstruct
    public void fillCache() {
        cache.addAll(todoRepository.findAll());
    }

    @Override
    public Todo add(Todo entity) {
        Todo toReturn = todoRepository.save(entity);
        entity.setId(toReturn.getId());
        cache.add(entity);
        prepareObservers(entity);
        return entity;
    }

    private void prepareObservers(Todo todo) {
        if (todo.getObservers().stream().anyMatch(observer -> observer instanceof ThirtyMinutesTimeObserver)) {
            todo.notifyObservers(todo.getExecutionDate());
        } else {
            todo.addObserver(new ThirtyMinutesTimeObserver(todo, this));
            todo.notifyObservers(todo.getExecutionDate());
        }
    }

    @Override
    public List<Todo> findAll() {
        return cache;
    }

    @Override
    public Todo getById(Integer id) {
        Todo entity = cache.stream().filter(object -> object.getId().equals(id)).findFirst().orElse(null);
        if (entity == null) {
            entity = todoRepository.findById(id).orElseThrow();
        }
        return entity;
    }

    @Override
    public Todo removeById(Integer id) {
        Todo entity = cache.stream().filter(object -> object.getId().equals(id)).findFirst().orElse(null);
        if (entity == null) {
            entity = todoRepository.findById(id).orElseThrow();
        }
        todoRepository.deleteById(entity.getId());
        entity.removeAllObservers();
        cache.remove(entity);

        return entity;
    }

    @Override
    public List<Todo> getTodayTodos() {
        return todoRepository.getTodayTodos();
    }
}

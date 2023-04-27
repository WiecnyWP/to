package com.wiecny.todoapp.component;

import com.wiecny.todoapp.model.Todo;
import com.wiecny.todoapp.repository.cache.TodoCache;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class ThirtyMinutesTimeObserver implements Observer {

    private Todo todo;
    private final TodoCache cache;

    public ThirtyMinutesTimeObserver(Todo todo, TodoCache cache) {
        this.todo = todo;
        this.cache = cache;
        todo.addObserver(this);
    }

    @Override
    public void update(LocalDateTime time) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(time) && now.plusMinutes(30).isAfter(time) && !todo.isWarning()) {
            log.warn("Time is within 30 minutes of current time for Todo: {}", todo.getId());
            todo.setWarning(true);
            cache.add(todo);
        }
    }

}

package com.wiecny.todoapp.component;

import java.time.LocalDateTime;

public interface Observer {

    void update(LocalDateTime time);
}

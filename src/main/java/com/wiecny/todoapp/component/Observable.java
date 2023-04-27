package com.wiecny.todoapp.component;

import java.time.LocalDateTime;

public interface Observable {

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers(LocalDateTime time);

    void removeAllObservers();
}

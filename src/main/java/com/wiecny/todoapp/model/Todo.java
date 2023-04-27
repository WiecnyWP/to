package com.wiecny.todoapp.model;

import com.wiecny.todoapp.component.Observable;
import com.wiecny.todoapp.component.Observer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Todo implements MyEntity, Prototype, Observable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column
    @NotNull
    private String description;
    @Column
    @Min(1)
    @Max(5)
    @NotNull
    private Integer priority;
    @Column
    @NotNull
    private LocalDateTime executionDate;
    @Column
    @NotNull
    private boolean done;
    @Column
    private boolean warning = false;

    @Transient
    @Builder.Default
    private List<Observer> observers = new ArrayList<>();

    @Override
    public Todo clone() {
        return Todo.builder()
                .description(getDescription())
                .priority(getPriority())
                .executionDate(getExecutionDate())
                .done(isDone())
                .build();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void removeAllObservers() {
        observers = new ArrayList<>();
    }

    @Override
    public void notifyObservers(LocalDateTime time) {
        for (Observer observer : observers) {
            observer.update(time);
        }
    }
}

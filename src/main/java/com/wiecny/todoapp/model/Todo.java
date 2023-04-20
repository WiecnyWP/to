package com.wiecny.todoapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Todo {

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
}

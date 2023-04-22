package com.wiecny.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {

    private String description;
    private Integer priority;
    private LocalDateTime executionDate;
    private boolean done;

    public static TodoDTOBuilder builder() {
        return new TodoDTOBuilder();
    }

    public static class TodoDTOBuilder {

        private String description;
        private Integer priority;
        private LocalDateTime executionDate;
        private boolean done;

        public TodoDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TodoDTOBuilder priority(Integer priority) {
            this.priority = priority;
            return this;
        }

        public TodoDTOBuilder executionDate(LocalDateTime executionDate) {
            this.executionDate = executionDate;
            return this;
        }

        public TodoDTOBuilder done(boolean done) {
            this.done = done;
            return this;
        }

        public TodoDTO build() {
            return new TodoDTO(description, priority, executionDate, done);
        }
    }
}




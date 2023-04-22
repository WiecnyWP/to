package com.wiecny.todoapp.controllers;

import com.wiecny.todoapp.dto.TodoDTO;
import com.wiecny.todoapp.model.MyEntity;
import com.wiecny.todoapp.model.Todo;
import com.wiecny.todoapp.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/todo")
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<TodoDTO> save(@RequestBody TodoDTO dto) {
        todoService.save(dto);
        return ResponseEntity.ok(dto);
    }

    @RequestMapping(value = "/done", method = RequestMethod.GET)
    public ResponseEntity markDone(@RequestParam int id) {
        todoService.markDone(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public ResponseEntity<TodoDTO> remove(@RequestParam int id) {
        TodoDTO dto = todoService.deleteById(id);
        return ResponseEntity.ok(dto);
    }

    @RequestMapping(value = "/getTodayTodos", method = RequestMethod.GET)
    public ResponseEntity<List<Todo>> getTodayTodos() {
        return ResponseEntity.ok(todoService.getTodayTodos());
    }

    @RequestMapping(value = "/getClone", method = RequestMethod.GET)
    public ResponseEntity<MyEntity> getCloneById(@RequestParam int id) {
        return ResponseEntity.ok(todoService.getCloneById(id));
    }
}

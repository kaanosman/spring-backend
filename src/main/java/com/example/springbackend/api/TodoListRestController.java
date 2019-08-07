package com.example.springbackend.api;

import com.example.springbackend.model.TodoList;
import com.example.springbackend.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TodoListRestController {
    @Autowired
    private TodoListService todoListService;

    @GetMapping("user/{userId}/todoList")
    public ResponseEntity<List<TodoList>> findAll(@PathVariable Long userId) {
        return ResponseEntity.ok(todoListService.findByUserId(userId));
    }

    @GetMapping("todoList/{id}")
    public ResponseEntity<TodoList> findById(@PathVariable Long id) {
        Optional<TodoList> todoList = todoListService.findById(id);
        if (!todoList.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(todoList.get());
    }

    @PostMapping("user/{userId}/todoList")
    public ResponseEntity create(@Valid @RequestBody TodoList todoList,  @PathVariable Long userId) {
        return ResponseEntity.ok(todoListService.save(todoList, userId));
    }

    @PutMapping("user/{userId}/todoList/{id}")
    public ResponseEntity<TodoList> update(@Valid @RequestBody TodoList todoList, @PathVariable Long id, @PathVariable Long userId) {
        if (!todoListService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(todoListService.save(todoList, userId));
    }

    @DeleteMapping("todoList/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (todoListService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        todoListService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}

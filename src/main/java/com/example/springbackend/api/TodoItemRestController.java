package com.example.springbackend.api;

import com.example.springbackend.model.TodoItem;
import com.example.springbackend.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TodoItemRestController {
    @Autowired
    private TodoItemService todoItemService;

    @GetMapping("todoItem")
    public ResponseEntity<List<TodoItem>> findByTodoListId() {
        return ResponseEntity.ok(todoItemService.findAll());
    }

    @GetMapping("/todoItem/{id}")
    public ResponseEntity<TodoItem> findById(@PathVariable Long id) {
        Optional<TodoItem> todoItem = todoItemService.findById(id);
        if (!todoItem.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(todoItem.get());
    }

    @PostMapping("todoList/{todoListId}/todoItem")
    public ResponseEntity create(@Valid @RequestBody TodoItem todoItem, @PathVariable Long todoListId) {
        return ResponseEntity.ok(todoItemService.save(todoItem, todoListId));
    }

    @PutMapping("todoList/{todoListId}/todoItem/{id}")
    public ResponseEntity<TodoItem> update(@PathVariable Long id, @Valid @RequestBody TodoItem todoItem, @PathVariable Long todoListId) {
        if (!todoItemService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(todoItemService.save(todoItem, todoListId));
    }

    @DeleteMapping("todoItem/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!todoItemService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        todoItemService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}

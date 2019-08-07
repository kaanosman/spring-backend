package com.example.springbackend.service;

import com.example.springbackend.model.TodoItem;
import com.example.springbackend.model.TodoList;
import com.example.springbackend.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoItemService {
    @Autowired
    private TodoItemRepository todoItemRepository;

    @Autowired
    private TodoListService todoListService;

    public List<TodoItem> findAll() {
        return todoItemRepository.findAll();
    }

    public Optional<TodoItem> findById(Long id) {
        return todoItemRepository.findById(id);
    }

    public TodoItem save(TodoItem todoItem, Long todoListId) {
        TodoList todoList = todoListService.findTodoListById(todoListId);
        todoItem.setTodolist(todoList);
        return todoItemRepository.save(todoItem);
    }

    public void deleteById(Long id) {
        todoItemRepository.deleteById(id);
    }
}

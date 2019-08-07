package com.example.springbackend.service;

import com.example.springbackend.model.TodoList;
import com.example.springbackend.model.User;
import com.example.springbackend.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TodoListService {
    @Autowired
    private TodoListRepository todoListRepository;

    @Autowired
    private UserService userService;

    public Optional<TodoList> findById(Long id) {
        return todoListRepository.findById(id);
    }

    public TodoList findTodoListById(Long id) {
        return todoListRepository.getOne(id);
    }

    public List<TodoList> findByUserId(Long userId) {
        return todoListRepository.findByUserId(userId);
    }

    public TodoList save(TodoList todoList, Long userId) {
        User user = userService.findUserById(userId);
        todoList.setUser(user);
        return todoListRepository.save(todoList);
    }

    public void deleteById(Long id) {
        todoListRepository.deleteById(id);
    }
}

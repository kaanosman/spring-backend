package com.example.springbackend.repository;

import com.example.springbackend.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
}

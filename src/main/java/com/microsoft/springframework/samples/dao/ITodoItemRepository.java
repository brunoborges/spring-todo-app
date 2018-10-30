package com.microsoft.springframework.samples.dao;

import com.microsoft.springframework.samples.model.TodoItem;

import java.util.Map;
import java.util.Optional;

public interface ITodoItemRepository {
    public Optional<TodoItem> findById(String index);

    Iterable<TodoItem> findAll();

    TodoItem save(TodoItem entity);

    void deleteById(String id);
}

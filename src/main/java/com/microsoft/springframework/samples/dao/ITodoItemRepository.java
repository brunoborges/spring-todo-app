package com.microsoft.springframework.samples.dao;

import java.util.Optional;
import com.microsoft.springframework.samples.model.TodoItem;

public interface ITodoItemRepository {
    public Optional<TodoItem> findById(String index);

    Iterable<TodoItem> findAll();

    TodoItem save(TodoItem entity);

    void deleteById(String id);
}

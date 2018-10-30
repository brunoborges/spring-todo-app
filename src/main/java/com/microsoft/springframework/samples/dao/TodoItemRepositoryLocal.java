package com.microsoft.springframework.samples.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import com.microsoft.springframework.samples.model.TodoItem;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"local"})
public class TodoItemRepositoryLocal implements ITodoItemRepository {

    private Map<String, TodoItem> data = new HashMap<>();

    @Override
    public TodoItem save(TodoItem entity) {
        return data.put(entity.getID(), entity);
    }

    @Override
    public Optional<TodoItem> findById(String s) {
        return Optional.of(data.get(s));
    }

    @Override
    public Iterable<TodoItem> findAll() {
        return StreamSupport.stream(data.values().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public void deleteById(String s) {
        data.remove(s);
    }

}

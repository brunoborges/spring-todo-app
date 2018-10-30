package com.microsoft.springframework.samples.dao;

import com.microsoft.springframework.samples.model.TodoItem;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Profile("local")
@Primary
@ConditionalOnMissingBean(name = "cosmosdb")
@Repository("local")
public class TodoItemRepositoryLocal implements PagingAndSortingRepository<TodoItem, String>, ITodoItemRepository {

    private Map<String, TodoItem> data = new HashMap<>();

    @Override
    public Iterable<TodoItem> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<TodoItem> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public TodoItem save(TodoItem entity) {
        return data.put(entity.getID(), entity);
    }

    @Override
    public <S extends TodoItem> Iterable<S> saveAll(Iterable<S> entities) {
        for (S s : entities) {
            data.put(s.getID(), s);
        }

        return entities;
    }

    @Override
    public Optional<TodoItem> findById(String s) {
        return Optional.of(data.get(s));
    }

    @Override
    public boolean existsById(String s) {
        return data.containsKey(s);
    }

    @Override
    public Iterable<TodoItem> findAll() {
        return StreamSupport.stream(data.values().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Iterable<TodoItem> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return data.size();
    }

    @Override
    public void deleteById(String s) {
        data.remove(s);
    }

    @Override
    public void delete(TodoItem entity) {
        data.values().remove(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends TodoItem> entities) {
        data.values().removeAll(StreamSupport.stream(entities.spliterator(), false).collect(Collectors.toList()));
    }

    @Override
    public void deleteAll() {
        data.clear();
    }
}

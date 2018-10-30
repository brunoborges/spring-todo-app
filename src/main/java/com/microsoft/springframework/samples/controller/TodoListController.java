/**
 * Copyright (c) Microsoft Corporation. All rights reserved. Licensed under the MIT License. See
 * LICENSE in the project root for license information.
 */
package com.microsoft.springframework.samples.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.microsoft.springframework.samples.dao.ITodoItemRepository;
import com.microsoft.springframework.samples.model.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TodoListController {

    @Autowired
    private ITodoItemRepository todoItemRepository;

    @GetMapping("/home")
    public Map<String, Object> home() {
        log.info("Hit /home");
        final Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "home");
        return model;
    }

    /**
     * HTTP GET
     */
    @GetMapping(value = "/api/todolist/{index}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTodoItem(@PathVariable("index") String index) {
        log.info("GET /api/todolist/{id} for " + index);
        try {
            return new ResponseEntity<TodoItem>(todoItemRepository.findById(index).get(),
                    HttpStatus.OK);
        } catch (Exception e) {
            log.error("GET", e);
            return new ResponseEntity<String>(index + " not found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * HTTP GET ALL
     */
    @GetMapping(value = "/api/todolist", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllTodoItems() {
        log.info("GET /api/todolist for all");
        try {
            return new ResponseEntity<>(todoItemRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("GET ALL", e);
            return new ResponseEntity<>("Nothing found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * HTTP POST NEW ONE
     */
    @PostMapping(value = "/api/todolist", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addNewTodoItem(@RequestBody TodoItem item) {
        log.info("POST /api/todolist to create item: " + item);
        try {
            item.setId(UUID.randomUUID().toString());
            todoItemRepository.save(item);
            return new ResponseEntity<String>(item.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("POST", e);
            return new ResponseEntity<String>("Entity creation failed", HttpStatus.CONFLICT);
        }
    }

    /**
     * HTTP PUT UPDATE
     */
    @PutMapping(value = "/api/todolist", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateTodoItem(@RequestBody TodoItem item) {
        log.info("PUT /api/todolist to to update item: " + item);
        try {
            todoItemRepository.deleteById(item.getId());
            todoItemRepository.save(item);
            return new ResponseEntity<String>("Entity updated", HttpStatus.OK);
        } catch (Exception e) {
            log.error("PUT", e);
            e.printStackTrace();
            return new ResponseEntity<String>("Entity updating failed", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * HTTP DELETE
     */
    @DeleteMapping(value = "/api/todolist/{id}")
    public ResponseEntity<String> deleteTodoItem(@PathVariable("id") String id) {
        log.error("DELETE /api/todolist/{id} for item: " + id);
        try {
            todoItemRepository.deleteById(id);
            return new ResponseEntity<String>("Entity deleted", HttpStatus.OK);
        } catch (Exception e) {
            log.error("DELETE", e);
            return new ResponseEntity<String>("Entity deletion failed", HttpStatus.NOT_FOUND);
        }

    }

}

package com.microsoft.springframework.samples.dao;

import com.microsoft.springframework.samples.model.TodoItem;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

/**
 * TodoItemRepositoryDynamoDB
 */
@EnableScan
@Profile({"dynamodb"})
public interface TodoItemRepositoryDynamoDB
        extends CrudRepository<TodoItem, String>, ITodoItemRepository {

}

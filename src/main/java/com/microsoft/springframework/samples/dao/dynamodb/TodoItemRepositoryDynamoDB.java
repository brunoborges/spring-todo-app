package com.microsoft.springframework.samples.dao.dynamodb;

import com.microsoft.springframework.samples.dao.ITodoItemRepository;
import com.microsoft.springframework.samples.model.TodoItem;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.context.annotation.Profile;

/**
 * TodoItemRepositoryDynamoDB
 */
@EnableScan
@Profile("dynamodb")
public interface TodoItemRepositoryDynamoDB extends DynamoDBCrudRepository<TodoItem, String>, ITodoItemRepository {

}

/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for
 * license information.
 */
package com.microsoft.springframework.samples.dao;

import com.microsoft.springframework.samples.model.TodoItem;
import com.microsoft.azure.spring.data.cosmosdb.repository.DocumentDbRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("!local")
@Repository("cosmosdb")
public interface TodoItemRepositoryCosmosDB extends DocumentDbRepository<TodoItem, String>, ITodoItemRepository {
}

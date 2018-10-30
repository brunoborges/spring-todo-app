/**
 * Copyright (c) Microsoft Corporation. All rights reserved. Licensed under the MIT License. See
 * LICENSE in the project root for license information.
 */
package com.microsoft.springframework.samples.dao.cosmosdb;

import com.microsoft.azure.spring.data.cosmosdb.repository.DocumentDbRepository;
import com.microsoft.springframework.samples.dao.ITodoItemRepository;
import com.microsoft.springframework.samples.model.TodoItem;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("cosmosdb")
@Repository
public interface TodoItemRepositoryCosmosDB
        extends DocumentDbRepository<TodoItem, String>, ITodoItemRepository {
}

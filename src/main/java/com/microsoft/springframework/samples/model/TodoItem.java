/**
 * Copyright (c) Microsoft Corporation. All rights reserved. Licensed under the MIT License. See
 * LICENSE in the project root for license information.
 */
package com.microsoft.springframework.samples.model;

import java.util.Objects;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document;

@Document(collection = "TodoItem")
@DynamoDBTable(tableName = "TodoItem")
public class TodoItem {

    private String id;
    private String description;
    private String owner;
    private boolean finished;

    public TodoItem() {}

    public TodoItem(String id, String description, String owner) {
        this.description = description;
        this.id = id;
        this.owner = owner;
        this.finished = false;
    }

    @DynamoDBAttribute
    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @DynamoDBAttribute
public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBAttribute
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @DynamoDBHashKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TodoItem)) {
            return false;
        }
        final TodoItem group = (TodoItem) o;
        return Objects.equals(this.getDescription(), group.getDescription())
                && Objects.equals(this.getOwner(), group.getOwner())
                && Objects.equals(this.getId(), group.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, id, owner);
    }

    @Override
    public String toString() {
        if (id != null)
            return id + ": " + description;
        else
            return description;
    }
}


package com.matrix.numbers.user.model;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.UpdateBehavior;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbUpdateBehavior;

import java.time.Instant;

@Data
public abstract class AbstractUser {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Instant regDate;


    @DynamoDbPartitionKey
    public String getId() {
        return getType() + "#"+ getEmail();
    }

    @DynamoDbUpdateBehavior(UpdateBehavior.WRITE_IF_NOT_EXISTS)
    public Instant getRegDate() {
        return regDate;
    }

    abstract String getType();
}

package com.matrix.numbers.user.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbIgnore;

@EqualsAndHashCode(callSuper = true)
@DynamoDbBean
@Data
@ToString(callSuper = true)
public class Customer extends AbstractUser {

    @Override
    @DynamoDbIgnore
    String getType() {
        return "CUSTOMER";
    }
}

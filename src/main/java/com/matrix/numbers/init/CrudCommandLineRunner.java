package com.matrix.numbers.init;

import com.matrix.numbers.user.model.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class CrudCommandLineRunner implements CommandLineRunner {
    private final DynamoDbAsyncClient asyncClient;
    private final DynamoDbEnhancedAsyncClient enhancedAsyncClient;


    public CrudCommandLineRunner(DynamoDbAsyncClient asyncClient, DynamoDbEnhancedAsyncClient enhancedAsyncClient) {
        this.asyncClient = asyncClient;
        this.enhancedAsyncClient = enhancedAsyncClient;
    }

    @Override
    public void run(String... args) {
        CompletableFuture<ListTablesResponse> listTablesResponseCompletableFuture = asyncClient.listTables();
        CompletableFuture<List<String>> listCompletableFuture = listTablesResponseCompletableFuture.thenApply(ListTablesResponse::tableNames);
        listCompletableFuture.thenAccept(tables -> {
            if (tables == null || !tables.contains(Customer.class.getSimpleName())) {
                DynamoDbAsyncTable<Customer> customer = enhancedAsyncClient.table(Customer.class.getSimpleName(), TableSchema.fromBean(Customer.class));
                customer.createTable();
            }
        });
    }
}

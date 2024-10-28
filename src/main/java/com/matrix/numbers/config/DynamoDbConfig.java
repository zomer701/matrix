package com.matrix.numbers.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

import java.net.URI;

@Configuration
public class DynamoDbConfig {

    private final String endPointUrl;
    private final String accessKeyId;
    private final String secretAccessKey;

    public DynamoDbConfig(@Value("${aws.dynamodb.endpoint}") String endPointUrl,
                          @Value("${aws.dynamodb.accessKeyId}") String accessKeyId,
                          @Value("${aws.dynamodb.secretAccessKey}") String secretAccessKey) {
        this.endPointUrl = endPointUrl;
        this.accessKeyId = accessKeyId;
        this.secretAccessKey = secretAccessKey;
    }


    @Bean
    public DynamoDbAsyncClient getDynamoDbAsyncClient() {

        return DynamoDbAsyncClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(
                        accessKeyId,
                        secretAccessKey)))
                .endpointOverride(URI.create(endPointUrl))
                .build();
    }

    @Bean
    public DynamoDbEnhancedAsyncClient getDynamoDbEnhancedAsyncClient() {
        return DynamoDbEnhancedAsyncClient.builder()
                .dynamoDbClient(getDynamoDbAsyncClient())
                .build();
    }
}

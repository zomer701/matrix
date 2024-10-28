package com.matrix.numbers.utils;

import java.util.Random;
import java.util.UUID;

public class MultiPartitionKeyGenerator {
    private static final int PREFIX_RANGE = 10; // Adjust range based on partition needs (e.g., 10 prefixes)

    // Generates a Partition Key with a random numeric prefix
    public static String generateRandomPrefixUUIDKey() {
        int randomPrefix = new Random().nextInt(PREFIX_RANGE); // Generates a random number from 0 to 9
        String uuid = UUID.randomUUID().toString();
        return randomPrefix + "-" + uuid; // Concatenate prefix with UUID
    }
}

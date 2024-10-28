package com.matrix.numbers.config;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PBKDF2PasswordHasher {
    private static final int SALT_LENGTH = 16; // Length of the salt
    private static final int ITERATIONS = 65536; // Number of iterations
    private static final int KEY_LENGTH = 256; // Derived key length in bits

    // Generate a random salt
    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }

    // Hash the password using PBKDF2WithHmacSHA256
    public static String hashPassword(String password) throws Exception {
        byte[] salt = generateSalt();
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = factory.generateSecret(spec).getEncoded();

        // Combine the salt and hash, and encode them in Base64
        String saltBase64 = Base64.getEncoder().encodeToString(salt);
        String hashBase64 = Base64.getEncoder().encodeToString(hash);
        return saltBase64 + ":" + hashBase64; // Store salt and hash together, separated by a colon
    }
}

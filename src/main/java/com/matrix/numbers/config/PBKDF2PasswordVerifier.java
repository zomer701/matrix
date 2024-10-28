package com.matrix.numbers.config;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;

public class PBKDF2PasswordVerifier {
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;

    // Verify the entered password against the stored hash
    public static boolean verifyPassword(String enteredPassword, String storedPassword) throws Exception {
        // Split the stored password into salt and hash
        String[] parts = storedPassword.split(":");
        String storedSaltBase64 = parts[0];
        String storedHashBase64 = parts[1];

        // Decode the Base64 encoded salt and hash
        byte[] salt = Base64.getDecoder().decode(storedSaltBase64);
        byte[] storedHash = Base64.getDecoder().decode(storedHashBase64);

        // Hash the entered password using the same salt
        PBEKeySpec spec = new PBEKeySpec(enteredPassword.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] enteredHash = factory.generateSecret(spec).getEncoded();

        // Compare the entered hash with the stored hash
        return slowEquals(storedHash, enteredHash);
    }

    // Constant-time comparison to prevent timing attacks
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }
}

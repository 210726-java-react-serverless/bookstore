package com.revature.bookstore.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

/**
 * The PasswordUtils class provides methods to generate encrypted passwords from plaintext, and to compare plaintext
 * passwords to encrypted passwords with their hash keys.
 *
 * Code provided by: Java T Point (https://www.javatpoint.com/how-to-encrypt-password-in-java)
 * Date: 06 August 2021
 */
public class PasswordUtils {

    private static final Random random = new SecureRandom();
    private static final String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int iterations = 10000;
    private static final int keylength = 256;

    /**
     * The getSalt method provides a "Salt value" which is used in encryption.
     * @param length - length of the hash key.
     * @return - final Salt value.
     */
    public static String getSalt(int length) {
        StringBuilder finalval = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            finalval.append(characters.charAt(random.nextInt(characters.length())));
        }

        return new String(finalval);
    }

    /**
     * The hash method hashes the plaintext password as a byte[] using the Salt value as a byte[].
     * @param password - plaintext password in char array.
     * @param salt - the hash key in byte array.
     * @return - hashed password as a byte array.
     */
    public static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keylength);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        }
        finally {
            spec.clearPassword();
        }
    }

    /**
     * The generateSecurePassword method fully encrypts a plaintext password via hash and Base64 encoding.
     * @param password - plaintext password to be encrypted.
     * @param salt - the hash key.
     * @return - final encrypted password.
     */
    public static String generateSecurePassword(String password, String salt) {
        String finalval = null;

        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());

        finalval = Base64.getEncoder().encodeToString(securePassword);

        return finalval;
    }

    /**
     * The verifyUserPassword compares a plaintext password to some encrypted password using its Salt value to encrypt
     * the plaintext and determine if the result is equivalent to the encrypted password.
     * @param providedPassword - plaintext password to check.
     * @param securedPassword - encrypted password to check against.
     * @param salt - hash key.
     * @return - true if encrypted provided password matches secured password; false otherwise.
     */
    public static boolean verifyUserPassword(String providedPassword,
                                             String securedPassword, String salt) {
        boolean finalval = false;

        /* Generate New secure password with the same salt */
        String newSecurePassword = generateSecurePassword(providedPassword, salt);

        /* Check if two passwords are equal */
        finalval = newSecurePassword.equalsIgnoreCase(securedPassword);

        return finalval;
    }
}

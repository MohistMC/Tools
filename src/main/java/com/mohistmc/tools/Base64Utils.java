package com.mohistmc.tools;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Utils {

    /**
     * Decode Base64 string
     * @param base64String Base64 encoded string
     * @return Decoded original string
     */
    public static String decodeBase64(String base64String) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64String);
            return new String(decodedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Encode string to Base64
     * @param originalString Original string
     * @return Base64 encoded string
     */
    public static String encodeBase64(String originalString) {
        try {
            byte[] encodedBytes = Base64.getEncoder().encode(originalString.getBytes(StandardCharsets.UTF_8));
            return new String(encodedBytes);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Check if string is valid Base64 encoding
     * @param str String to check
     * @return Whether it is valid Base64 encoding
     */
    public static boolean isValidBase64(String str) {
        if (str == null || str.length() % 4 != 0) {
            return false;
        }

        try {
            Base64.getDecoder().decode(str);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

package com.mohistmc.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import lombok.SneakyThrows;

/**
 * @author Mgazul
 * @date 2025/2/12 14:40
 */
public class SHA256 {

    /**
     * Get the SHA-256 value of this input stream
     *
     * @param is
     * @return
     */
    @SneakyThrows
    public static String as(InputStream is) {
        try (InputStream inputStream = is) {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] byteArray = new byte[1024];
            int bytesCount;

            while ((bytesCount = inputStream.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }

            // 将字节数组转换为十六进制字符串
            byte[] bytes = digest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        }
    }

    /**
     * Get the SHA-256 value of the file
     *
     * @param file
     * @return
     */
    @SneakyThrows
    public static String as(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            return as(fis);
        }
    }

    /**
     * Get the SHA-256 value of the specified file or path
     *
     * @param filePath
     * @return
     */
    @SneakyThrows
    public static String as(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            return as(fis);
        }
    }

    /**
     * Check the SHA-256 value of this input stream
     *
     * @param is
     * @param toBeCheckSum
     * @return
     */
    @SneakyThrows
    public static boolean is(InputStream is, String toBeCheckSum) {
        return as(is).equals(toBeCheckSum);
    }

    /**
     * Check the SHA-256 value of the file
     *
     * @param file
     * @param toBeCheckSum
     * @return
     */
    @SneakyThrows
    public static boolean is(File file, String toBeCheckSum) {
        return as(file).equals(toBeCheckSum);
    }

    /**
     * Check the SHA-256 value of the specified path file
     *
     * @param path
     * @param toBeCheckSum
     * @return
     */
    @SneakyThrows
    public static boolean is(String path, String toBeCheckSum) {
        return as(path).equals(toBeCheckSum);
    }
}

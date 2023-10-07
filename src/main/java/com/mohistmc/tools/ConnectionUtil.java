package com.mohistmc.tools;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Mgazul by MohistMC
 * @date 2023/10/7 0:58:48
 */
public class ConnectionUtil {

    public static boolean hasUrl(String s) {
        try {
            URL url = new URL(s);
            url.openStream();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static URLConnection getConn(String URL) {
        URLConnection conn;
        try {
            conn = URI.create(URL).toURL().openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:31.0) Gecko/20100101 Firefox/31.0");
        } catch (IOException e) {
            return null;
        }
        return conn;
    }

    public static String getSize(long size) {
        return (size >= 1048576L) ? (float) size / 1048576.0F + "MB" : ((size >= 1024) ? (float) size / 1024.0F + "KB" : size + "B");
    }

    public static boolean isDown(String s) {
        try {
            HttpURLConnection httpUrlConnection = (HttpURLConnection) URI.create(s).toURL().openConnection();
            httpUrlConnection.connect();
            return httpUrlConnection.getResponseCode() != 200;
        } catch (Exception e) {
            return true;
        }
    }

    public static long getUrlMillis(String link) {
        try {
            HttpURLConnection connection = (HttpURLConnection) URI.create(link).toURL().openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            long start = System.currentTimeMillis();
            int responseCode = connection.getResponseCode();
            long end = System.currentTimeMillis();
            return end - start;
        } catch (Exception e) {
            return -0L;
        }
    }
}

package com.mohistmc.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Mgazul by MohistMC
 * @date 2023/9/10 19:24:26
 */
public class FileUtils {

    public static List<String> readFileFromJar(ClassLoader classLoader, String path) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(path)))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public static void deleteFolders(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    deleteFolders(f);
                }
            }
        }
        file.delete();
    }
}

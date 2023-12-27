package com.mohistmc.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class MojangEulaUtil {

    private static final File eula = new File("eula.txt");
    private static final File globalEula = new File(System.getProperty("user.home"), "eula.txt");

    public static void writeInfos(String eulaText) throws IOException {
        eula.createNewFile();
        BufferedWriter b = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("eula.txt"), StandardCharsets.UTF_8));
        b.write(eulaText);
        b.close();
    }

    public static boolean hasAcceptedEULA() throws IOException {
        return (globalEula.exists() && Files.readAllLines(globalEula.toPath()).contains("eula=true")) || (eula.exists() && Files.readAllLines(eula.toPath()).contains("eula=true"));
    }
}

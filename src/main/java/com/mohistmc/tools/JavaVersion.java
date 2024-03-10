package com.mohistmc.tools;

import java.util.Optional;

public class JavaVersion {

    public static int asInt() {
        String version = System.getProperty("java.version");
        if (version.startsWith("1.")) {
            version = version.substring(2);
        }

        int dot = version.indexOf(".");
        if (dot != -1) {
            version = version.substring(0, dot);
        }
        Integer versionInt = NumberUtil.toInt(version);
        return Optional.ofNullable(versionInt).orElse(0);
    }

    public static String asClass() {
        return System.getProperty("java.class.version");
    }

    public static String as() {
        return System.getProperty("java.version");
    }
}

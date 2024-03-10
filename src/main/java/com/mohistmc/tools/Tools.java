package com.mohistmc.tools;

public class Tools {

    public static String version() {
        return (Tools.class.getPackage().getImplementationVersion() != null) ? Tools.class.getPackage().getImplementationVersion() : "dev";
    }
}

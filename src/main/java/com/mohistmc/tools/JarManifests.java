package com.mohistmc.tools;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.jar.Manifest;

public final class JarManifests {

    private static final Map<ClassLoader, Manifest> MANIFESTS = Collections.synchronizedMap(new WeakHashMap<>());

    public static Manifest manifest(final Class<?> clazz) {
        return MANIFESTS.computeIfAbsent(clazz.getClassLoader(), classLoader -> {
            final String classLocation = "/" + clazz.getName().replace(".", "/") + ".class";
            final URL resource = clazz.getResource(classLocation);
            if (resource == null) {
                return null;
            }
            final String classFilePath = resource.toString().replace("\\", "/");
            final String archivePath = classFilePath.substring(0, classFilePath.length() - classLocation.length());
            try (final InputStream stream = new URL(archivePath + "/META-INF/MANIFEST.MF").openStream()) {
                return new Manifest(stream);
            } catch (final IOException ex) {
                return null;
            }
        });
    }
}

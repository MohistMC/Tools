/*
 * Mohist - MohistMC
 * Copyright (C) 2018-2023.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package com.mohistmc.tools;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * @Author Mgazul
 * @create 2019/10/12 20:26
 */
public class JarTool {

    private final Class<?> classz;

    public JarTool(Class<?> classz) {
        this.classz = classz;
    }

    public String getJarPath() {
        File file = getFile();
        if (file == null) {
            return null;
        }
        return file.getAbsolutePath();
    }

    public File getJarDir() {
        File file = getFile();
        if (file == null) {
            return null;
        }
        return file.getParentFile();
    }

    public String getJarName() {
        File file = getFile();
        if (file == null) {
            return null;
        }
        return file.getName();
    }

    public File getFile() {
        String path = classz.getProtectionDomain().getCodeSource().getLocation().getFile();
        path = java.net.URLDecoder.decode(path, StandardCharsets.UTF_8);
        return new File(path);
    }
}

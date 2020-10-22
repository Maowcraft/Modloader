package maow.modloader.util;

import maow.modloader.mod.ModContainer;
import maow.modloader.mod.ModInfo;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

public class IOUtil {
    public static List<File> getFilesInDirectory(String path) {
        List<File> files = new ArrayList<>();
        File dir = new File(path);
        if (dir.exists() && dir.isDirectory()) {
            files.addAll(Arrays.asList(Objects.requireNonNull(dir.listFiles())));
        }
        return files;
    }

    public static JarEntry findModInfoEntry(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            JarInputStream jis = new JarInputStream(fis);
            JarEntry entry = jis.getNextJarEntry();
            while (entry != null) {
                entry = jis.getNextJarEntry();
                if (entry.getName().endsWith("mod.json")) {
                    return entry;
                }
            }
            jis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ModInfo getModInfo(InputStream stream) {
        List<String> strings = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            while (reader.ready()) {
                strings.add(reader.readLine());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return GsonUtil.readModInfo(StringUtil.collapseStrings(strings));
    }

    public static List<ModContainer> getModContainers(String path) {
        List<ModContainer> containers = new ArrayList<>();
        try {
            List<File> files = getFilesInDirectory(path);
            for (File file : files) {
                JarEntry jarEntry = findModInfoEntry(file);
                if (jarEntry != null) {
                    JarFile jarFile = new JarFile(file);
                    InputStream stream = jarFile.getInputStream(jarEntry);
                    containers.add(new ModContainer(getModInfo(stream), file));
                    stream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return containers;
    }
}

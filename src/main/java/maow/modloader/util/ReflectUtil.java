package maow.modloader.util;

import maow.modloader.api.Entrypoint;
import maow.modloader.mod.ModContainer;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class ReflectUtil {
    public static void loadModEntrypoints(ModContainer container) {
        String[] entrypoints = container.getModInfo().getEntrypoints();
        File jarFile = container.getJarFile();
        for (String entrypoint : entrypoints) {
            try {
                URLClassLoader classLoader = new URLClassLoader(new URL[]{jarFile.toURI().toURL()});
                Class<?> clazz = Class.forName(entrypoint, true, classLoader);
                Entrypoint obj = (Entrypoint) clazz.newInstance();
                clazz.getMethod("onInit").invoke(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

package maow.modloader.api;

import maow.modloader.mod.ModContainer;
import maow.modloader.util.IOUtil;
import maow.modloader.util.ReflectUtil;

import java.util.HashMap;
import java.util.Map;

public class Modloader {
    private static final Map<String, ModContainer> loadedMods = new HashMap<>();

    public static void initModloader(String modsPath) {
        for (ModContainer container : IOUtil.getModContainers(modsPath)) {
            System.out.println("Modloader has loaded : " + container.getModInfo().getName() + " [" + container.getModInfo().getVersion() + "]");
            loadMod(container);
        }
    }

    public static void loadMod(ModContainer container) {
        loadedMods.put(container.getModInfo().getId(), container);
        ReflectUtil.loadModEntrypoints(container);
    }

    public static Map<String, ModContainer> getLoadedMods() {
        return loadedMods;
    }
}

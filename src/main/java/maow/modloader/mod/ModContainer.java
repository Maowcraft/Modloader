package maow.modloader.mod;

import java.io.File;

public class ModContainer {
    private final ModInfo modInfo;
    private final File jarFile;

    public ModContainer(ModInfo modInfo, File jarFile) {
        this.modInfo = modInfo;
        this.jarFile = jarFile;
    }

    public ModInfo getModInfo() {
        return modInfo;
    }

    public File getJarFile() {
        return jarFile;
    }
}

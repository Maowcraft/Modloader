package maow.modloader.mod;

public class ModInfo {
    private final String name;
    private final String id;
    private final String version;
    private final String description;
    private final String author;
    private final String[] entrypoints;

    public ModInfo(String name, String id, String version, String description, String author, String[] entrypoints) {
        this.name = name;
        this.id = id;
        this.version = version;
        this.description = description;
        this.author = author;
        this.entrypoints = entrypoints;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String[] getEntrypoints() {
        return entrypoints;
    }
}

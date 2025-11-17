package org.unitedlands.classes;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.unitedlands.interfaces.IMessageProvider;

public abstract class AbstractConfigMessageProvider implements IMessageProvider {

    protected FileConfiguration config;

    protected AbstractConfigMessageProvider(FileConfiguration config) {
        this.config = config;
    }

    @Override
    public void reload(FileConfiguration config) {
        this.config = config;
    }

    @Override
    public String get(String key) {
        return config.getString(key, "<missing: " + key + ">");
    }

    @Override
    public List<String> getList(String key) {
        return config.getStringList(key);
    }

}
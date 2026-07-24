package org.unitedlands.classes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, String> getSection(String sectionKey) {
        var section = config.getConfigurationSection(sectionKey);
        if (section == null)
            return new HashMap<>();

        Map<String, String> keyValues = new HashMap<>();
        for (var key : section.getKeys(false)) {
            keyValues.put(key, section.getString(key));
        }
        return keyValues;
    }

}
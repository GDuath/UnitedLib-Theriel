package org.unitedlands.interfaces;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

public interface IMessageProvider {
    void reload(FileConfiguration config);
    String get(String key);
    List<String> getList(String key);
}

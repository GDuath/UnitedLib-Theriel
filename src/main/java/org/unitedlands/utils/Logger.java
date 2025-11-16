package org.unitedlands.utils;

import org.unitedlands.UnitedLib;

public class Logger {

    private static final UnitedLib plugin;

    static {
        plugin = UnitedLib.getPlugin(UnitedLib.class);
    }

    public static void log(String message) {
        plugin.getLogger().info(message);
    }

    public static void logWarning(String message) {
        plugin.getLogger().warning(message);
    }

    public static void logError(String message) {
        plugin.getLogger().severe(message);
    }
}
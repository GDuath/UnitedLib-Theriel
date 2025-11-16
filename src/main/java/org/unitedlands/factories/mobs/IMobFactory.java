package org.unitedlands.factories.mobs;

import java.util.UUID;

import org.bukkit.Location;

public interface IMobFactory {
    UUID createMobAtLocation(String mobType, Location location);
}

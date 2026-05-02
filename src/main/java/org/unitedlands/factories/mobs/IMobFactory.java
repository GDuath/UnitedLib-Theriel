package org.unitedlands.factories.mobs;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface IMobFactory {
    UUID createMobAtLocation(String mobType, Location location);
    UUID createMobAtLocation(String mobType, Location location, double level);
    UUID createMobAtLocation(String mobType, Location location, Player owner, double level);
}

package org.unitedlands.factories.mobs;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class BaseMobFactory implements IMobFactory {

    public BaseMobFactory() {

    }

    @Override
    public abstract UUID createMobAtLocation(String mobType, Location location);

    @Override
    public abstract UUID createMobAtLocation(String mobType, Location location, double level);

    @Override
    public abstract UUID createMobAtLocation(String mobType, Location location, double level, String faction);

    public abstract UUID createMobAtLocation(String mobType, Location location, Player owner, double level,
            String faction);

    public abstract void setName(UUID id, String name);
            

}

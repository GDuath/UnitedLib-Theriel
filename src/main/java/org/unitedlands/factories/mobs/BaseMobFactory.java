package org.unitedlands.factories.mobs;

import java.util.UUID;

import org.bukkit.Location;

public abstract class BaseMobFactory implements IMobFactory {

    public BaseMobFactory() {

    }

    @Override
    public abstract UUID createMobAtLocation(String mobType, Location location);

}

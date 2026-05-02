package org.unitedlands.factories.mobs;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.unitedlands.utils.Logger;

public class VanillaMobFactory extends BaseMobFactory {

    public VanillaMobFactory() {

    }

    public UUID createMobAtLocation(String mobType, Location location) {
        return createMobAtLocation(mobType, location, 1);
    }

    @Override
    public UUID createMobAtLocation(String mobType, Location location, double level) {
        try {
            var entityType = EntityType.valueOf(mobType);
            var entity = (LivingEntity) location.getWorld().spawnEntity(location, entityType);

            return entity.getUniqueId();
        } catch (Exception ex) {
            Logger.logError("Error creating entity: " + ex.getMessage());
        }
        return null;
    }

    public UUID createMobAtLocation(String mobType, Location location, Player owner, double level) {
        Logger.logError("Unable to create mob " + mobType + " with owner, vanilla mobs can't be owned.");
        return null;
    }

}

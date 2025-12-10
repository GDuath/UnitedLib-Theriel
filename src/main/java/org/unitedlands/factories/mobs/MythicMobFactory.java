package org.unitedlands.factories.mobs;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.unitedlands.utils.Logger;

import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;

public class MythicMobFactory extends BaseMobFactory {

    public MythicMobFactory() {

    }

    @Override
    public UUID createMobAtLocation(String mobType, Location location) {
        var mythicMob = MythicBukkit.inst().getMobManager().getMythicMob(mobType).orElse(null);
        if (mythicMob != null) {
            ActiveMob activeMythicMob = MythicBukkit.inst().getMobManager().spawnMob(mobType, location);
            return activeMythicMob.getUniqueId();
        } else {
            try {
                var entityType = EntityType.valueOf(mobType);
                var entity = (LivingEntity) location.getWorld().spawnEntity(location, entityType, SpawnReason.CUSTOM);

                return entity.getUniqueId();
            } catch (Exception ex) {
                Logger.logError("Error creating entity: " + ex.getMessage());
            }
        }
        return null;
    }

}

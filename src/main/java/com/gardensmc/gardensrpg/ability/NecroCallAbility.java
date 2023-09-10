package com.gardensmc.gardensrpg.ability;

import com.gardensmc.gardensrpg.ability.types.Ability;
import com.gardensmc.gardensrpg.ability.types.Cast;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

public class NecroCallAbility extends Ability implements Cast {

    private static final int TICK_DEATH = 400;

    @Override
    public int getCoolDown() {
        return 30;
    }


    @Override
    public void cast(Player player) {
        int mobCount = 5;
        for (int i = 0; i < mobCount; i++) {
            Location location = player.getLocation().add(
                    getRandOffset(),
                    0,
                    getRandOffset()
            );
            // ensure ground level
            location = player.getWorld().getHighestBlockAt(location).getLocation().add(0, 1, 0);
            // summon mobs
            Entity entity = player.getWorld().spawnEntity(location, EntityType.SKELETON);
            NBTCompound nbt = new NBTEntity(entity).getPersistentDataContainer();
            nbt.setUUID("NecroCaller", player.getUniqueId());
            nbt.setInteger("TickDeath", TICK_DEATH);
        }
    }

    private int getRandOffset() {
        int s = (ThreadLocalRandom.current().nextBoolean()) ? 1 : -1;
        return ThreadLocalRandom.current().nextInt(1, 6) * s;
    }
}

package com.gardensmc.gardensrpg.scheduler.schedules;

import com.gardensmc.gardensrpg.GardensRPG;
import com.gardensmc.gardensrpg.scheduler.Schedule;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

public class NecroMobDespawnSchedule extends Schedule {

    @Override
    public void schedule() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(GardensRPG.plugin, () -> {
            for (World world : Bukkit.getWorlds()) {
                for (LivingEntity livingEntity : world.getLivingEntities()) {
                    NBTCompound nbt = new NBTEntity(livingEntity).getPersistentDataContainer();
                    if (nbt.hasTag("TickDeath")) {
                        int tickDeath = nbt.getInteger("TickDeath");
                        if (livingEntity.getTicksLived() >= tickDeath) {
                            livingEntity.remove();
                            // TODO spawn cute poof particle
                        }
                    }
                }
            }
        }, 0, 20);
    }
}

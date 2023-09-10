package com.gardensmc.gardensrpg.scheduler;

import com.gardensmc.gardensrpg.GardensRPG;
import org.bukkit.Bukkit;

public class AbilityCooldownsSchedule extends Schedule {

    @Override
    public void schedule() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(GardensRPG.plugin, () -> {
            GardensRPG.playerCooldownsCache.tickCache();
        }, 0, 20);
    }
}

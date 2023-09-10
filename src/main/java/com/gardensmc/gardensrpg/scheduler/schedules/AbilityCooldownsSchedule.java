package com.gardensmc.gardensrpg.scheduler.schedules;

import com.gardensmc.gardensrpg.GardensRPG;
import com.gardensmc.gardensrpg.scheduler.Schedule;
import org.bukkit.Bukkit;

public class AbilityCooldownsSchedule extends Schedule {

    @Override
    public void schedule() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(GardensRPG.plugin, () -> {
            GardensRPG.playerCooldownsCache.tickCache();
        }, 0, 20);
    }
}

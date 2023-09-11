package com.gardensmc.gardensrpg.listener;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;


public class MobListener implements Listener {

    /**
     * Cancel necro called mobs from targeting their callers
     */
    @EventHandler
    public void onMobTarget(EntityTargetLivingEntityEvent e) {
        if (e.getTarget() instanceof Player player) {
            NBTCompound nbt = new NBTEntity(e.getEntity()).getPersistentDataContainer();
            if (nbt.hasTag("NecroCaller") && player.getUniqueId().equals(nbt.getUUID("NecroCaller"))) {
                e.setCancelled(true);
            }
        }
    }

    /**
     * Prevent necro called entities from burning in the sun
     */
    @EventHandler
    public void onMobBurn(EntityCombustEvent e) {
        // ignore burns from attacks
        if (e instanceof EntityCombustByEntityEvent || e instanceof EntityCombustByBlockEvent) {
            return;
        }
        NBTCompound nbt = new NBTEntity(e.getEntity()).getPersistentDataContainer();
        if (nbt.hasTag("NecroCaller")) {
            e.setCancelled(true);
        }
    }
}

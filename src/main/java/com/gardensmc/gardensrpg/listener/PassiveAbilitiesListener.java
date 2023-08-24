package com.gardensmc.gardensrpg.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PassiveAbilitiesListener implements Listener {

    @EventHandler
    public void onPlayerTakeAttackDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player player) {
            // todo get the player's bladebringer type
            //  if implement castevent check it here and cast if met
        }
    }
}

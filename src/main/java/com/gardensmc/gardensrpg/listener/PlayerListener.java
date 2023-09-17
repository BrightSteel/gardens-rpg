package com.gardensmc.gardensrpg.listener;

import com.gardensmc.gardensrpg.GardensRPG;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onSwapHands(PlayerSwapHandItemsEvent e) {
        if (holdingBlade(e.getPlayer())) {
            GardensRPG.castActiveAbilityHandler.castActive(e.getPlayer());
            e.setCancelled(true);
        }
    }

    private boolean holdingBlade(Player player) {
        return player.getInventory().getItemInMainHand().getType().toString().contains("SWORD");
    }
}

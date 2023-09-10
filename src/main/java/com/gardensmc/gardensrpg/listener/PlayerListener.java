package com.gardensmc.gardensrpg.listener;

import com.gardensmc.gardensrpg.GardensRPG;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerListener implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getHand() == EquipmentSlot.HAND && holdingBlade(e.getPlayer())) {
                GardensRPG.castActiveAbilityHandler.toggleAbility(e.getPlayer());
            }
        }
    }

    @EventHandler
    public void onLeftClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (e.getHand() == EquipmentSlot.HAND && holdingBlade(e.getPlayer())) {
                GardensRPG.castActiveAbilityHandler.castIfPrimed(e.getPlayer());
            }
        }
    }

    private boolean holdingBlade(Player player) {
        return player.getInventory().getItemInMainHand().getType().toString().contains("SWORD");
    }
}

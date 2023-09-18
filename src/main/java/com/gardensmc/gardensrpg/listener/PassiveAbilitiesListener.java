package com.gardensmc.gardensrpg.listener;

import com.gardensmc.gardensrpg.GardensRPG;
import com.gardensmc.gardensrpg.ability.types.EventCast;
import com.gardensmc.gardensrpg.bladebringers.BladeBringer;
import com.gardensmc.gardensrpg.database.entry.PlayerBladeBringerEntry;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

public class PassiveAbilitiesListener implements Listener {

    @EventHandler
    public void onPlayerTakeAttackDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player player) {
            castPassiveIfApplicable(player, e);
        }
    }

    @EventHandler
    public void onMobTargetPlayer(EntityTargetLivingEntityEvent e) {
        if (e.getTarget() instanceof Player player) {
            castPassiveIfApplicable(player, e);
        }
    }

    private void castPassiveIfApplicable(Player player, Event e) {
        PlayerBladeBringerEntry playerEntry = GardensRPG.playerEntryCache.getActiveBladeBringer(player.getUniqueId());
        if (playerEntry != null) {
            BladeBringer bladeBringer = GardensRPG.bladeBringerHandler.getBladeBringer(playerEntry.getBlade_bringer());
            if (bladeBringer.getPassiveAbility() instanceof EventCast eventCast) {
                if (eventCast.getCastEvent().isInstance(e)) {
                    eventCast.cast(e, player);
                }
            }
        }
    }
}

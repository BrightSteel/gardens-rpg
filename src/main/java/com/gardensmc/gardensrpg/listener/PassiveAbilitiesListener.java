package com.gardensmc.gardensrpg.listener;

import com.gardensmc.gardensrpg.GardensRPG;
import com.gardensmc.gardensrpg.ability.types.EventCast;
import com.gardensmc.gardensrpg.bladebringers.BladeBringer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PassiveAbilitiesListener implements Listener {

    @EventHandler
    public void onPlayerTakeAttackDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player player) {
            castPassiveIfApplicable(player, e);
        }
    }

    private void castPassiveIfApplicable(Player player, Event e) {
        GardensRPG.playerEntryCache.get(player.getUniqueId()).thenAccept(playerEntry -> {
            BladeBringer bladeBringer = GardensRPG.bladeBringerHandler
                    .getBladeBringer(playerEntry.getBladeBringer());
            if (bladeBringer.getPassiveAbility() instanceof EventCast eventCast) {
                if (eventCast.getCastEvent().isInstance(e)) {
                    eventCast.cast(e, player);
                }
            }
        });
    }
}

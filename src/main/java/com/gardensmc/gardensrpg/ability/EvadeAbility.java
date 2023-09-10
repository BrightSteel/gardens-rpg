package com.gardensmc.gardensrpg.ability;

import com.gardensmc.gardensrpg.ability.types.Ability;
import com.gardensmc.gardensrpg.ability.types.EventCast;
import com.gardensmc.gardensrpg.util.ChatUtil;
import com.gardensmc.gardensrpg.util.ProbabilityUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EvadeAbility extends Ability implements EventCast {

    private static final double CAST_CHANCE = 10;

    @Override
    public Class<? extends Event> getCastEvent() {
        return EntityDamageByEntityEvent.class;
    }

    @Override
    public void cast(Event event, Player player) {
        if (ProbabilityUtil.roll(CAST_CHANCE)) {
            if (event instanceof EntityDamageByEntityEvent entityDamageByEntityEvent) {
                entityDamageByEntityEvent.setCancelled(true);
                if (entityDamageByEntityEvent.getDamager() instanceof Player damager) {
                    ChatUtil.sendMessage(damager, String.format("%s dodged your attack!", player.getName()));
                }
                ChatUtil.sendMessage(player, "That attack slipped right off you!");
            }
        }
    }

    @Override
    public int getCoolDown() {
        return 2;
    }
}

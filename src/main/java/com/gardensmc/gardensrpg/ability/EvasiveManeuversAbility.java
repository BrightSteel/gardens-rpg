package com.gardensmc.gardensrpg.ability;

import com.gardensmc.gardensrpg.ability.types.Ability;
import com.gardensmc.gardensrpg.ability.types.EventCast;
import com.gardensmc.gardensrpg.util.ProbabilityUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EvasiveManeuversAbility extends Ability implements EventCast {

    private static final double CAST_CHANCE = 0.10;

    @Override
    public Class<? extends Event> getCastEvent() {
        return EntityDamageByEntityEvent.class;
    }

    @Override
    public void cast(Event event, Player player) {
        if (ProbabilityUtil.roll(CAST_CHANCE)) {
            if (event instanceof Cancellable cancellable) {
                cancellable.setCancelled(true);
            }
        }
    }

    @Override
    public int getCoolDown() {
        return 0;
    }
}

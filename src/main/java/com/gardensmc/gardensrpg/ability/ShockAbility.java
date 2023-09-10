package com.gardensmc.gardensrpg.ability;

import com.gardensmc.gardensrpg.ability.types.Ability;
import com.gardensmc.gardensrpg.ability.types.EventCast;
import com.gardensmc.gardensrpg.util.ChatUtil;
import com.gardensmc.gardensrpg.util.ProbabilityUtil;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ShockAbility extends Ability implements EventCast {

    private static final int CAST_CHANCE = 10;
    private static final int SHOCK_AMOUNT = 1;

    @Override
    public int getCoolDown() {
        return 5;
    }

    @Override
    public Class<? extends Event> getCastEvent() {
        return EntityDamageByEntityEvent.class;
    }

    @Override
    public void cast(Event event, Player player) {
        if (ProbabilityUtil.roll(CAST_CHANCE)) {
            if (event instanceof EntityDamageByEntityEvent entityDamageByEntityEvent) {
                if (entityDamageByEntityEvent.getDamager() instanceof LivingEntity damager) {
                    damager.damage(SHOCK_AMOUNT, player);
                    damager.setVelocity(damager.getLocation().getDirection().multiply(-1));
                    damager.setFireTicks(damager.getFireTicks() + 30);
                }
            }
        }
    }
}

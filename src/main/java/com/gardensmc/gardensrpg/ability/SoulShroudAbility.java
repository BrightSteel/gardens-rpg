package com.gardensmc.gardensrpg.ability;

import com.gardensmc.gardensrpg.ability.types.Ability;
import com.gardensmc.gardensrpg.ability.types.EventCast;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

import java.util.Set;

public class SoulShroudAbility extends Ability implements EventCast {

    private static final Set<EntityType> undeadMobs = Set.of(
            EntityType.SKELETON_HORSE, EntityType.SKELETON, EntityType.WITHER_SKELETON, EntityType.ZOMBIE,
            EntityType.ZOMBIE_HORSE, EntityType.HUSK, EntityType.DROWNED, EntityType.PHANTOM,
            EntityType.STRAY, EntityType.WITHER, EntityType.ZOMBIE_VILLAGER, EntityType.ZOMBIFIED_PIGLIN
    );

    @Override
    public int getCoolDown() {
        return 0;
    }

    @Override
    public Class<? extends Event> getCastEvent() {
        return EntityTargetLivingEntityEvent.class;
    }

    @Override
    public void cast(Event event, Player player) {
        if (event instanceof EntityTargetLivingEntityEvent e) {
            if (e.getReason() != EntityTargetEvent.TargetReason.TARGET_ATTACKED_ENTITY) {
                if (e.getTarget() instanceof Player && undeadMobs.contains(e.getEntityType())) {
                    e.setCancelled(true);
                }
            }
        }
    }
}

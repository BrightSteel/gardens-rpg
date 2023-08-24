package com.gardensmc.gardensrpg.ability;

import com.gardensmc.gardensrpg.ability.types.Ability;
import com.gardensmc.gardensrpg.ability.types.CastEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EvasiveManeuversAbility extends Ability implements CastEvent {

    @Override
    public Class<? extends Event> getCastEvent() {
        return EntityDamageByEntityEvent.class;
    }

    @Override
    public void cast(Player player) {

    }

    @Override
    public int getCoolDown() {
        return 0;
    }
}

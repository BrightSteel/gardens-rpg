package com.gardensmc.gardensrpg.ability.types;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public interface EventCast {
    Class<? extends Event> getCastEvent();
    void cast(Event event, Player player);
}

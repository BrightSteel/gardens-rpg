package com.gardensmc.gardensrpg.ability.types;

import org.bukkit.event.Event;

public interface CastEvent {
    Class<? extends Event> getCastEvent();
}

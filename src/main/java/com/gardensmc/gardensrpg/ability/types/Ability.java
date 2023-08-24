package com.gardensmc.gardensrpg.ability.types;

import org.bukkit.entity.Player;

public abstract class Ability {
    public abstract void cast(Player player);
    public abstract int getCoolDown();
}
